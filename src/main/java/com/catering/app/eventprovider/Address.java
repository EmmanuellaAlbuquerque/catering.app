package com.catering.app.eventprovider;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(name = "zipcode", nullable = false)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "event_provider_id")
    private EventProvider eventProvider;

    @Deprecated
    public Address() {}

    public Address(String neighborhood, String state, String city, String zipCode) {
        this.neighborhood = neighborhood;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }
}
