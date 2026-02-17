package com.catering.app.eventprovider.domain.dto;

public record AddressData(
        String neighborhood,
        String state,
        String city,
        String zipCode
) {}
