package org.post.office.mailing.controller;

import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.repository.PostalItemRepository;
import org.post.office.mailing.service.PostalItemService;
import org.post.office.mailing.service.PostalOfficeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
public class PostalItemController {
    private final PostalItemRepository repository;

    public PostalItemController(PostalItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("actual")
    public List<PostalItemEntity> getAllActualPostalItems() {
        try {
            return repository.findAllWhereDeletedFalse();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

    @GetMapping("archive")
    public List<PostalItemEntity> getAllArchivePostalItems() {
        try {
            return repository.findAllWhereDeletedTrue();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }
}
