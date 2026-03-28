package com.luml.domain;

import lombok.Data;

import java.util.Optional;

/**
 * @author luml
 * @description
 * @date 2026/3/28
 */
public class Address {
    private String city;
    private String street;

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
