package ru.demi.model;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Address homeAddress;

    @AttributeOverride(
        name = "country",
        column = @Column(name = "BILLING_COUNTRY")
    )
    @AttributeOverride(
        name = "region",
        column = @Column(name = "BILLING_REGION")
    )
    @AttributeOverride(
        name = "city",
        column = @Column(name = "BILLING_CITY")
    )
    @AttributeOverride(
        name = "street",
        column = @Column(name = "BILLING_STREET")
    )
    @AttributeOverride(
        name = "zipCode",
        column = @Column(name = "BILLING_ZIPCODE")
    )
    private Address billingAddress;
}
