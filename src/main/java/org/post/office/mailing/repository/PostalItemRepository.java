package org.post.office.mailing.repository;

import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.repository.customized.BaseRepository;
import org.post.office.mailing.repository.customized.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalItemRepository extends BaseRepository<PostalItemEntity, Long>, CustomizedRepository<PostalItemEntity> {
}
