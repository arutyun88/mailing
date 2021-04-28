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
@Table(name = "postal_office")
@NoArgsConstructor
@AllArgsConstructor
public class PostalOfficeEntity implements BaseEntity {

    @Id @NotNull
    private String postalCode;
    private String name;
    private String address;
    private boolean deleted;

    @Override
    public boolean getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "PostalOffice{" +
                "postalCode='" + postalCode + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
