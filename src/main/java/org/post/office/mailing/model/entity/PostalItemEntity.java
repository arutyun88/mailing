package org.post.office.mailing.model.entity;

import lombok.*;
import org.post.office.mailing.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Entity
@Table(name = "postal_item")
@NoArgsConstructor
@AllArgsConstructor
public class PostalItemEntity implements BaseEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_sequence")
    @SequenceGenerator(name = "custom_sequence", initialValue = 1000000000, allocationSize = 0)
    private long id;

    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private PostalItemType postalType;

    @ManyToOne
    @JoinColumn(name = "receiver_postal_code")
    @NotNull
    private PostalOfficeEntity receiverPostalCode;

    private String receiverAddress;

    private String receiverName;

    @Override
    public boolean getDeleted() {
        return false;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
