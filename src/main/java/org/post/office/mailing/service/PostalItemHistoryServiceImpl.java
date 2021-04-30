package org.post.office.mailing.service;

import lombok.extern.slf4j.Slf4j;
import org.post.office.mailing.model.document.PostalItemHistoryDocument;
import org.post.office.mailing.model.dto.PostalItemHistoryDto;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.repository.PostalItemHistoryRepository;
import org.post.office.mailing.repository.PostalItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostalItemHistoryServiceImpl implements PostalItemHistoryService {
    private final PostalItemHistoryRepository historyRepository;
    private final PostalItemRepository itemRepository;
    private final PostalItemHistoryMapping historyMapping;

    public PostalItemHistoryServiceImpl(
            PostalItemHistoryRepository historyRepository,
            PostalItemRepository itemRepository, PostalItemHistoryMapping historyMapping) {
        this.historyRepository = historyRepository;
        this.itemRepository = itemRepository;
        this.historyMapping = historyMapping;
    }

    @Override
    public ResponseEntity<?> getHistoryByPostalItem(long postalItem) {
        PostalItemEntity postalItemEntity = itemRepository.findById(postalItem).orElse(null);
        if (postalItemEntity != null) {
            List<PostalItemHistoryDocument> result = historyRepository.getAllByPostalItemEntity(postalItemEntity);
            PostalItemHistoryDto dto = historyMapping.mappingHistoryDocumentsToDto(postalItem, result);
            return ResponseEntity.ok().body(dto);
        } else {
            log.warn("Postal item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
