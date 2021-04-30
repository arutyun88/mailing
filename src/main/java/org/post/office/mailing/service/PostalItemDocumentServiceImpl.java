package org.post.office.mailing.service;

import lombok.extern.slf4j.Slf4j;
import org.post.office.mailing.model.document.PostalItemDocument;
import org.post.office.mailing.model.document.PostalItemHistoryDocument;
import org.post.office.mailing.model.document.PostalItemStatus;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.repository.PostalItemDocumentRepository;
import org.post.office.mailing.repository.PostalItemHistoryRepository;
import org.post.office.mailing.repository.PostalItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class PostalItemDocumentServiceImpl implements PostalItemDocumentService {
    private final PostalItemService itemService;
    private final PostalItemDocumentRepository documentRepository;
    private final PostalItemRepository itemRepository;
    private final PostalItemHistoryRepository historyRepository;

    public PostalItemDocumentServiceImpl(
            PostalItemService itemService,
            PostalItemDocumentRepository documentRepository, PostalItemRepository itemRepository, PostalItemHistoryRepository historyRepository) {
        this.itemService = itemService;
        this.documentRepository = documentRepository;
        this.itemRepository = itemRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public ResponseEntity<?> createDocument(PostalItemDocument itemDocument) {
        ResponseEntity<?> resultItemCreate = itemService.createPostalItem(itemDocument.getPostalItem());
        if (resultItemCreate.getStatusCode().is2xxSuccessful()) {
            itemDocument.setRegistrationDate(new Date());
            itemDocument.setUpdateDate(new Date());
            itemDocument.setStatus(PostalItemStatus.REGISTERED);
            itemDocument.setDeleted(false);
            itemDocument.setCurrentOffice(itemDocument.getRegistrationOffice());
            documentRepository.save(itemDocument);
            historyRepository.save(PostalItemHistoryDocument.builder()
                    .eventDate(itemDocument.getUpdateDate())
                    .status(itemDocument.getStatus())
                    .postalItemEntity(itemDocument.getPostalItem())
                    .postalOffice(itemDocument.getCurrentOffice())
                    .build());
            log.info("Postal item registered");
            return ResponseEntity.ok().build();
        }
        log.warn("Postal item was not registered");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Override
    public ResponseEntity<?> sendingPostalItem(long postalItem) {
        ResponseEntity<?> findResult = itemRepository.findByIdWhereDeletedFalse(postalItem);
        if (findResult.getStatusCode().is2xxSuccessful()) {
            PostalItemEntity itemEntity = (PostalItemEntity) findResult.getBody();
            PostalItemDocument document = documentRepository.findByPostalItem(itemEntity);
            if (!PostalItemStatus.ON_ROUTE.equals(document.getStatus())) {
                document.setStatus(PostalItemStatus.ON_ROUTE);
                document.setUpdateDate(new Date());
                documentRepository.save(document);
                historyRepository.save(PostalItemHistoryDocument.builder()
                        .eventDate(document.getUpdateDate())
                        .status(document.getStatus())
                        .postalItemEntity(document.getPostalItem())
                        .postalOffice(document.getCurrentOffice())
                        .build());
                log.info("Postal item on route");
                return ResponseEntity.ok().build();
            } else {
                log.warn("Postal item already on route");
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        log.warn("Postal item not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<?> getPostalItemStatus(long postalItem) {
        ResponseEntity<?> itemResult = itemRepository.findByIdWhereDeletedFalse(postalItem);
        if (itemResult.getStatusCode().is2xxSuccessful()) {
            PostalItemEntity postalItemEntity = (PostalItemEntity) itemResult.getBody();
            PostalItemDocument postalDocument = documentRepository.findByPostalItem(postalItemEntity);
            log.info("Status received");
            return ResponseEntity.ok().body(postalDocument.getStatus());
        }
        log.warn("Postal item not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<?> changeCurrentPoint(long postalItem) {
        ResponseEntity<?> itemResult = itemRepository.findByIdWhereDeletedFalse(postalItem);
        if (itemResult.getStatusCode().is2xxSuccessful()) {
            PostalItemEntity postalItemEntity = (PostalItemEntity) itemResult.getBody();
            PostalItemDocument postalDocument = documentRepository.findByPostalItem(postalItemEntity);
            if (postalDocument.getStatus().equals(PostalItemStatus.ON_ROUTE)) {
                postalDocument.setStatus(PostalItemStatus.POINT);
                postalDocument.setCurrentOffice(postalDocument.getDestinationOffice());
                log.info("Postal item on route");
            } else if (postalDocument.getStatus().equals(PostalItemStatus.POINT)) {
                postalDocument.setStatus(PostalItemStatus.COURIER);
                log.info("Postal item on courier");
            } else if (postalDocument.getStatus().equals(PostalItemStatus.COURIER)) {
                postalDocument.setStatus(PostalItemStatus.DELIVERED);
                postalDocument.setDeleted(true);
                assert postalItemEntity != null;
                postalItemEntity.setDeleted(true);
                itemRepository.save(postalItemEntity);
                log.info("Postal item on delivered");
            } else {
                log.warn("Postal item already delivered");
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            postalDocument.setUpdateDate(new Date());
            documentRepository.save(postalDocument);
            historyRepository.save(PostalItemHistoryDocument.builder()
                    .eventDate(postalDocument.getUpdateDate())
                    .status(postalDocument.getStatus())
                    .postalItemEntity(postalDocument.getPostalItem())
                    .postalOffice(postalDocument.getCurrentOffice())
                    .build());
            return ResponseEntity.ok().build();
        }
        log.warn("Postal item not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
