package com.catering.app.eventprovider;

import jakarta.persistence.*;

@Entity
public class EventProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trading_name", nullable = false)
    private String tradingName;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String description;

    @Deprecated
    public EventProvider() {}

    public EventProvider(String tradingName, String companyName, String registrationNumber, String description) {
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.description = description;
    }
}
