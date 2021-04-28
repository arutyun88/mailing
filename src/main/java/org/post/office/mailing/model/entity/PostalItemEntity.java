package org.post.office.mailing.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private long id;
    private boolean deleted;
    private String postalType;
    private String postalCode;
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

    @Override
    public String toString() {
        return "PostalItemEntity{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", postalType='" + postalType + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                '}';
    }
}
