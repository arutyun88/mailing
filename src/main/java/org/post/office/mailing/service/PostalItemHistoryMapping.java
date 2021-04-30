package org.post.office.mailing.service;

import org.post.office.mailing.model.document.PostalItemHistoryDocument;
import org.post.office.mailing.model.dto.PostalItemHistoryDto;

import java.util.List;

public interface PostalItemHistoryMapping {
    PostalItemHistoryDto mappingHistoryDocumentsToDto(long postalItem, List<PostalItemHistoryDocument> historyDocuments);
}
