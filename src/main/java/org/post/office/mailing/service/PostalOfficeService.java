package org.post.office.mailing.service;

import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostalOfficeService {
    void createPostalOffice(PostalOfficeEntity postalOfficeEntity);
    void editPostalOffice(PostalOfficeEntity postalOfficeEntity);
    ResponseEntity<?> findPostalOfficeByPostalCode(String postalCode);
    List<PostalOfficeEntity> findAllPostalOffice();
    void deletePostalOfficeByPostalCode(String postalCode);
    long getCountPostalOffices();
    List<PostalOfficeEntity> findAllPostalOfficeWhereStatusDeleted();
}
