package org.post.office.mailing.repository;

import org.post.office.mailing.model.document.PostalItemDocument;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.repository.customized.BaseRepository;
import org.post.office.mailing.repository.customized.CustomizedPostalOfficeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalItemDocumentRepository extends BaseRepository<PostalItemDocument, Long>, CustomizedPostalOfficeRepository<PostalItemDocument> {
    PostalItemDocument findByPostalItem(PostalItemEntity postalItemEntity);
}
