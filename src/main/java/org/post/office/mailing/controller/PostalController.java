package org.post.office.mailing.controller;

import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.service.PostalOfficeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
public class PostalController {
    private final PostalOfficeServiceImpl service;

    public PostalController(PostalOfficeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("offices")
    public List<PostalOfficeEntity> getAllPostalOffices() {
        try {
            return service.findAllPostalOffice();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

    @GetMapping("deleted/offices")
    public List<PostalOfficeEntity> getAllDeletedPostalOffices() {
        try {
            return service.findAllPostalOfficeWhereStatusDeleted();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

    @GetMapping("office/{postalCode}")
    public ResponseEntity<?> getPostalOfficeByPostalCode(@PathVariable("postalCode") String postalCode) {
        ResponseEntity<?> responseEntity = service.findPostalOfficeByPostalCode(postalCode);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok().body(responseEntity.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("add/office")
    public void addPostalOffice(@RequestBody PostalOfficeEntity postalOfficeEntity) {
        service.createPostalOffice(postalOfficeEntity);
    }

    @GetMapping("delete/office/{postalCode}")
    public void deletePostalOffice(@PathVariable("postalCode") String postalCode) {
        service.deletePostalOfficeByPostalCode(postalCode);
    }
}
