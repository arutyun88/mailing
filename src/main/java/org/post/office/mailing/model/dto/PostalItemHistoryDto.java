package org.post.office.mailing.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalItemHistoryDto {
    private String number;
    private String currentStatus;
    private List<PostalItemEvent> events;
}
