package org.post.office.mailing.model.document;

import lombok.*;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.model.entity.PostalOfficeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postal_item_history")
public class PostalItemHistoryDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne @NotNull @JoinColumn(name = "postal_item")
    private PostalItemEntity postalItemEntity;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "status", length = 10) @Enumerated(EnumType.STRING)
    private PostalItemStatus status;

    @ManyToOne @NotNull @JoinColumn(name = "postal_office")
    private PostalOfficeEntity postalOffice;
}
