package ru.demi.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity for RegistrationAddress.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(value = "registration")
public class RegistrationAddress extends Address {
}
