package org.post.office.mailing.controller;

import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.service.PostalOfficeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
public class PostalController {
    private final PostalOfficeServiceImpl service;

    public PostalController(PostalOfficeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public List<PostalOfficeEntity> getAll() {
        try {
            return service.findAllPostalOffice();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

    @GetMapping("deleted")
    public List<PostalOfficeEntity> getAllDeleted() {
        try {
            return service.findAllPostalOfficeWhereStatusDeleted();
        } catch (EntityNotFoundException exception) {
            return Collections.emptyList();
        }
    }

//    todo
    @PostMapping("add")
    public void add() {
        PostalOfficeEntity postalOfficeEntity = PostalOfficeEntity.builder()
                .postalCode("309502")
                .name("Старый Оскол 16")
                .address("Белгородская область, город Старый Оскол")
                .deleted(false)
                .build();
        service.createPostalOffice(postalOfficeEntity);
    }

//    todo
    @GetMapping("delete")
    public void delete() {
        System.out.println(service.findAllPostalOffice());
        try {
            service.deletePostalOfficeByPostalCode("309502");
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getClass().getSimpleName());
            System.out.println("Объект не найден");
        }
        System.out.println(service.findAllPostalOffice());
    }
}
