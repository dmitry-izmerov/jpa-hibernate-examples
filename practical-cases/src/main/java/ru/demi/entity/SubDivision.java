package ru.demi.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Subdivision of organization.
 */
@ToString(exclude = {"organization"})
@Data
@Accessors(chain = true)
@Entity(name = "subdivision")
@DiscriminatorValue("subdivision")
public class SubDivision extends Organization {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Organization organization;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubDivision)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        SubDivision that = (SubDivision) o;

        return getFullName() != null ? getFullName().equals(that.getFullName()) : that.getFullName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        return result;
    }
}
