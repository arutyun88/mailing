package org.post.office.mailing.service;

import org.springframework.http.ResponseEntity;

public interface PostalItemHistoryService {
    ResponseEntity<?> getHistoryByPostalItem(long postalItem);
}
