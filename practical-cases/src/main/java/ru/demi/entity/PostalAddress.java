package ru.demi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity for PostalAddress.
 */
@Entity
@DiscriminatorValue(value = "postal")
public class PostalAddress extends Address {
}
