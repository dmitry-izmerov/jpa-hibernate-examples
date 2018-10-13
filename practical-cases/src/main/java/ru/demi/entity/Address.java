package ru.demi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;

/**
 * Address entity.
 */
@Data
@Accessors(chain = true)
@Entity(name = "address")
@EqualsAndHashCode(of = {"id"})
@DiscriminatorOptions(force = true)
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "zip_code")
    private String zipCode;

    private String country;

    private String area;

    private String district;

    private String place;

    private String street;

    private String house;

    private String building;

    private String flat;

    private String region;

    @Column(name = "full_address")
    private String fullAddress;

    private String city;

    private String subject;
}
