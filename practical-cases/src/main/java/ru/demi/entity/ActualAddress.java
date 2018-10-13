package ru.demi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity for ActualAddress.
 */
@Entity
@DiscriminatorValue(value = "actual")
public class ActualAddress extends Address {
}
