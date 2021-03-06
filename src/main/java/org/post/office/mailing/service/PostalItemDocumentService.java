package org.post.office.mailing.service;

import org.post.office.mailing.model.document.PostalItemDocument;
import org.springframework.http.ResponseEntity;

public interface PostalItemDocumentService {
    ResponseEntity<?> createDocument(PostalItemDocument itemDocument);
    ResponseEntity<?> sendingPostalItem(long postalItem);
    ResponseEntity<?> getPostalItemStatus(long postalItem);
    ResponseEntity<?> changeCurrentPoint(long postalItem);
}
