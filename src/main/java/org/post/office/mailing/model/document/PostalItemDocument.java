package org.post.office.mailing.model.document;

import lombok.*;
import org.post.office.mailing.model.BaseEntity;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.post.office.mailing.model.entity.PostalOfficeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "postal_item_document")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalItemDocument implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne @NotNull @JoinColumn(name = "postal_item")
    private PostalItemEntity postalItem;

    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToOne @NotNull @JoinColumn(name = "registration_office")
    private PostalOfficeEntity registrationOffice;

    @ManyToOne @NotNull @JoinColumn(name = "current_office")
    private PostalOfficeEntity currentOffice;

    @ManyToOne @NotNull @JoinColumn(name = "destination_office")
    private PostalOfficeEntity destinationOffice;

    @Column(name = "status", length = 10) @Enumerated(EnumType.STRING)
    private PostalItemStatus status;

    @Column(name = "deleted")
    private boolean deleted;

    @Override
    public boolean getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
