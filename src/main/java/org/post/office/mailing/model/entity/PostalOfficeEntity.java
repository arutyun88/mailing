package org.post.office.mailing.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@Entity
@Table(name = "postal_office")
@NoArgsConstructor
@AllArgsConstructor
public class PostalOfficeEntity implements BaseEntity {

    @Id @NotNull
    @Pattern(regexp = "[0-9]{6}")
    @Column(name = "postal_code", length = 6)
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
}
