package org.post.office.mailing.service;

import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.repository.PostalItemRepository;
import org.post.office.mailing.repository.PostalOfficeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostalItemServiceImpl implements PostalItemService {
    private final PostalOfficeRepository officeRepository;
    private final PostalItemRepository itemRepository;

    public PostalItemServiceImpl(
            PostalOfficeRepository officeRepository,
            PostalItemRepository itemRepository) {
        this.officeRepository = officeRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ResponseEntity<?> createPostalItem(PostalItemEntity postalItemEntity) {
        ResponseEntity<?> responseEntity =
                officeRepository.findByIdWhereDeletedFalse(postalItemEntity.getReceiverPostalCode().getPostalCode());
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            PostalOfficeEntity officeEntity = (PostalOfficeEntity) responseEntity.getBody();
            postalItemEntity.setReceiverPostalCode(officeEntity);
            itemRepository.save(postalItemEntity);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(responseEntity.getStatusCode().value()).build();
    }
}
