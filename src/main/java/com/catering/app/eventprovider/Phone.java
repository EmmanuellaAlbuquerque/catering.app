package com.catering.app.eventprovider;

import jakarta.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "event_provider_id")
    private EventProvider eventProvider;

    @Deprecated
    public Phone() {}

    public Phone(String number) {
        this.number = number;
    }
}
