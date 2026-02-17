package com.catering.app.eventprovider.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventProviderUpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String tradingName;

    @NotBlank
    private String companyName;

    @NotBlank
    private String registrationNumber;

    @NotBlank
    private String description;

    @NotEmpty
    private List<String> phones = new ArrayList<>();

    @NotEmpty
    private List<String> emails = new ArrayList<>();

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    public EventProviderUpdateRequest(Long id, String tradingName, String companyName, String registrationNumber, String description, List<String> phones, List<String> emails, String neighborhood, String state, String city, String zipCode) {
        this.id = id;
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.description = description;
        this.phones = phones;
        this.emails = emails;
        this.neighborhood = neighborhood;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
