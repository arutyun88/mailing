package org.post.office.mailing.service;

import org.post.office.mailing.model.entity.PostalOfficeEntity;

import java.util.List;
import java.util.Optional;

public interface PostalOfficeService {
    void createPostalOffice(PostalOfficeEntity postalOfficeEntity);
    void editPostalOffice(PostalOfficeEntity postalOfficeEntity);
    PostalOfficeEntity findPostalOfficeByPostalCode(String postalCode);
    List<PostalOfficeEntity> findAllPostalOffice();
    void deletePostalOfficeByPostalCode(String postalCode);
    long getCountPostalOffices();
    Optional<?> findPostalOfficeByPostalCodeAndName(String postalCode, String name);
    List<PostalOfficeEntity> findAllPostalOfficeWhereStatusDeleted();
}
