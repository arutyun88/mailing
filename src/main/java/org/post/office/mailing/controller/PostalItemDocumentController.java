package org.post.office.mailing.controller;

import org.post.office.mailing.model.document.PostalItemDocument;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.service.PostalItemDocumentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
