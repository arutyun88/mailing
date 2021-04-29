package org.post.office.mailing.service;

import org.post.office.mailing.model.document.PostalItemDocument;
import org.post.office.mailing.model.document.PostalItemStatus;
import org.post.office.mailing.repository.PostalItemDocumentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostalItemDocumentServiceImpl implements PostalItemDocumentService {
    private final PostalItemService itemService;
    private final PostalItemDocumentRepository documentRepository;

    public PostalItemDocumentServiceImpl(
            PostalItemService itemService,
            PostalItemDocumentRepository documentRepository) {
        this.itemService = itemService;
        this.documentRepository = documentRepository;
    }

    @Override
    public ResponseEntity<?> createDocument(PostalItemDocument itemDocument) {
        ResponseEntity<?> resultItemCreate = itemService.createPostalItem(itemDocument.getPostalItem());
        if (resultItemCreate.getStatusCode().is2xxSuccessful()) {
            itemDocument.setRegistrationDate(new Date());
            itemDocument.setStatus(PostalItemStatus.REGISTERED);
            itemDocument.setDeleted(false);
            itemDocument.setCurrentOffice(itemDocument.getRegistrationOffice());
            documentRepository.save(itemDocument);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(400).build();
    }
}
