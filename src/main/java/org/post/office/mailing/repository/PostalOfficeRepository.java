package org.post.office.mailing.repository;

import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.repository.customized.BaseRepository;
import org.post.office.mailing.repository.customized.CustomizedPostalOfficeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalOfficeRepository extends BaseRepository<PostalOfficeEntity, String>, CustomizedPostalOfficeRepository<PostalOfficeEntity> {
}