package org.post.office.mailing.controller;

import org.post.office.mailing.model.document.PostalItemDocument;
import org.post.office.mailing.service.PostalItemDocumentServiceImpl;
import org.post.office.mailing.service.PostalItemHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostalItemDocumentController {
    private final PostalItemDocumentServiceImpl documentService;
    private final PostalItemHistoryService historyService;

    public PostalItemDocumentController(
            PostalItemDocumentServiceImpl documentService,
            PostalItemHistoryService historyService) {
        this.documentService = documentService;
        this.historyService = historyService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createDocument(@RequestBody PostalItemDocument itemDocument){
        return documentService.createDocument(itemDocument);
    }

    @PostMapping("sending/{postalItem}")
    public ResponseEntity<?> sendingPostalItem(@PathVariable("postalItem") long postalItem) {
        return documentService.sendingPostalItem(postalItem);
    }

    @GetMapping("status/{postalItem}")
    public ResponseEntity<?> getStatus(@PathVariable("postalItem") long postalItem) {
        return documentService.getPostalItemStatus(postalItem);
    }

    @PostMapping("point/{postalItem}")
    public ResponseEntity<?> changeCurrentPoint(@PathVariable("postalItem") long postalItem) {
        return documentService.changeCurrentPoint(postalItem);
    }

    @GetMapping("history/{postalItem}")
    public ResponseEntity<?> getHistoryPostalItem(@PathVariable("postalItem") long postalItem) {
        return historyService.getHistoryByPostalItem(postalItem);
    }
}
