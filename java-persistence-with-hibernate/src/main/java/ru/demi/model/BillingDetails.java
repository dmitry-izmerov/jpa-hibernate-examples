package ru.demi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class BillingDetails {

    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    protected String owner;
}
