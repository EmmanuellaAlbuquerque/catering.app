package com.catering.app.eventprovider;

import jakarta.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Deprecated
    public Phone() {}

    public Phone(String number) {
        this.number = number;
    }
}
