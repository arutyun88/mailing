package org.post.office.mailing.service;

import org.post.office.mailing.model.entity.PostalItemEntity;
import org.springframework.http.ResponseEntity;

public interface PostalItemService {
    ResponseEntity<?> createPostalItem(PostalItemEntity entity);
}
