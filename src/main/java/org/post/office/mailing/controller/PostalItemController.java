package org.post.office.mailing.controller;

import lombok.extern.slf4j.Slf4j;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.repository.PostalItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
public class PostalItemController {
    private final PostalItemRepository repository;

    public PostalItemController(PostalItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("item/actual")
    public List<PostalItemEntity> getAllActualPostalItems() {
        try {
            return repository.findAllWhereDeletedFalse();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

    @GetMapping("item/archive")
    public List<PostalItemEntity> getAllArchivePostalItems() {
        try {
            return repository.findAllWhereDeletedTrue();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

    @GetMapping("item/getAll")
    public List<PostalItemEntity> getAllPostalItems() {
        try {
            return repository.findAll();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

    @GetMapping("item/{postalItem}")
    public ResponseEntity<?> getPostalItemById(@PathVariable("postalItem") long postalItem) {
        PostalItemEntity result = repository.findById(postalItem).orElse(null);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        } else {
            log.warn("Postal item " + postalItem + " not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
