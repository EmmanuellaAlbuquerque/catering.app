package com.catering.app.eventprovider.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class Phone {

    @Column(nullable = false)
    private String number;

    @Deprecated
    public Phone() {}

    public Phone(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(number, phone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
