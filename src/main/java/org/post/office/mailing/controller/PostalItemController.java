package org.post.office.mailing.controller;

import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.service.PostalItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostalItemController {
    private final PostalItemService itemService;

    public PostalItemController(PostalItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("add/item")
    public ResponseEntity<?> addPostalItem(@RequestBody PostalItemEntity postalItemEntity) {
        return itemService.createPostalItem(postalItemEntity);
    }
}
