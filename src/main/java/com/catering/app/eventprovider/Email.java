package com.catering.app.eventprovider;

import jakarta.persistence.*;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_provider_id")
    private EventProvider eventProvider;

    @Deprecated
    public Email() {}

    public Email(String email) {
        this.email = email;
    }
}
