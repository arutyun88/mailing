package org.post.office.mailing.repository;

import org.post.office.mailing.model.document.PostalItemHistoryDocument;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostalItemHistoryRepository extends JpaRepository<PostalItemHistoryDocument, Long> {
    List<PostalItemHistoryDocument> getAllByPostalItemEntity(PostalItemEntity postalItemEntity);
}
