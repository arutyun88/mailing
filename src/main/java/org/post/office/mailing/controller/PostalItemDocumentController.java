package org.post.office.mailing.controller;

import org.post.office.mailing.model.document.PostalItemDocument;
import org.post.office.mailing.service.PostalItemDocumentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostalItemDocumentController {
    private final PostalItemDocumentServiceImpl documentService;

    public PostalItemDocumentController(PostalItemDocumentServiceImpl documentService) {
        this.documentService = documentService;
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
}
