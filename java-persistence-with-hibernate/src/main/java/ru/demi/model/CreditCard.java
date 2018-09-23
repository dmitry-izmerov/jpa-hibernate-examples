package ru.demi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(callSuper = true)
@Entity
public class CreditCard extends BillingDetails {

    @NotNull
    private String cardNumber;

    @NotNull
    private String expMonth;

    @NotNull
    private String expYear;
}
