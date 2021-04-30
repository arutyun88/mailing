package org.post.office.mailing.repository;

import org.post.office.mailing.model.document.PostalItemHistoryDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalItemHistoryRepository extends JpaRepository<PostalItemHistoryDocument, Long> {
}
