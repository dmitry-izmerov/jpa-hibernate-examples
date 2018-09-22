package ru.demi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotNull
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(nullable = false)
    private String region;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false, length = 6)
    private String zipCode;
}
