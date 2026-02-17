package com.catering.app.eventprovider.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class Email {

    @Column(nullable = false)
    private String email;

    @Deprecated
    public Email() {}

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
