package com.catering.app.eventprovider;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "eventProvider", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phone> phones = new HashSet<>();

    @OneToMany(mappedBy = "eventProvider", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Email> emails = new HashSet<>();

    @OneToMany(mappedBy = "eventProvider", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "event_provider_payment_method",
            joinColumns = @JoinColumn(name = "event_provider_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

    @Deprecated
    public EventProvider() {}

    public EventProvider(String tradingName, String companyName, String registrationNumber, String description) {
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.description = description;
    }
}
