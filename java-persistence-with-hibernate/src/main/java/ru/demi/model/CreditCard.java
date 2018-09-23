package ru.demi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "creditCardId")
public class CreditCard extends BillingDetails {

    @NotNull
    private String cardNumber;

    @NotNull
    private String expMonth;

    @NotNull
    private String expYear;
}
