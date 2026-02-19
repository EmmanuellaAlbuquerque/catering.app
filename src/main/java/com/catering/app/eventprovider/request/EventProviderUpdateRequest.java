package com.catering.app.eventprovider.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class EventProviderUpdateRequest extends EventProviderBaseRequest {

    @NotNull
    private final Long id;

    private List<String> urlImages;

    public EventProviderUpdateRequest(Long id, String tradingName, String companyName, String registrationNumber, String description, List<String> phones, List<String> emails, String neighborhood, String state, String city, String zipCode, List<String> urlImages) {
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
        this.urlImages = urlImages;
    }

    public Long getId() {
        return id;
    }

    public List<String> getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(List<String> urlImages) {
        this.urlImages = urlImages;
    }
}
