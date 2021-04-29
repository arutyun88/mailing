package org.post.office.mailing.repository;

import org.post.office.mailing.model.document.PostalItemDocument;
import org.post.office.mailing.repository.customized.BaseRepository;
import org.post.office.mailing.repository.customized.CustomizedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalItemDocumentRepository extends BaseRepository<PostalItemDocument, Long>, CustomizedRepository<PostalItemDocument> {
}
