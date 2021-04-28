package org.post.office.mailing.repository;

import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.repository.customized.BaseRepository;
import org.post.office.mailing.repository.customized.CustomizedRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryRepository extends BaseRepository<PostalOfficeEntity, String>, CustomizedRepository<PostalOfficeEntity> {
    Optional<?> findByPostalCodeAndName(String postalCode, String name);
}