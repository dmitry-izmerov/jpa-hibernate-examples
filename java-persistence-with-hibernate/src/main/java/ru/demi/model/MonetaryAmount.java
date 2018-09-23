package ru.demi.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

@Data
public class MonetaryAmount implements Serializable {
    private final BigDecimal value;
    private final Currency currency;

    @Override
    public String toString() {
        return value + " " + currency;
    }

    public static MonetaryAmount of(String amount) {
        String[] split = amount.split(" ");
        return new MonetaryAmount(new BigDecimal(split[0]), Currency.getInstance(split[1]));
    }
}