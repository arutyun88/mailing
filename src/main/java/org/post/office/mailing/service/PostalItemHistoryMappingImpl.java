package org.post.office.mailing.service;

import org.post.office.mailing.model.document.PostalItemHistoryDocument;
import org.post.office.mailing.model.dto.PostalItemEvent;
import org.post.office.mailing.model.dto.PostalItemHistoryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostalItemHistoryMappingImpl implements PostalItemHistoryMapping {
    @Override
    public PostalItemHistoryDto mappingHistoryDocumentsToDto(long postalItem, List<PostalItemHistoryDocument> historyDocuments) {
        List<PostalItemEvent> events = new ArrayList<>();
        String status = "";
        for (PostalItemHistoryDocument document : historyDocuments) {
            events.add(PostalItemEvent.builder()
                    .date(document.getEventDate().toString())
                    .event(document.getPostalOffice().getPostalCode() + " / " + document.getStatus().toString())
                    .build());
            status = document.getStatus().toString();
        }
        return PostalItemHistoryDto.builder()
                .number(String.valueOf(postalItem))
                .events(events)
                .currentStatus(status)
                .build();
    }
}
