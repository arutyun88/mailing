package org.post.office.mailing.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalItemEvent {
    private String date;
    private String event;
}
