package com.catering.app.eventprovider.request;

import com.catering.app.eventprovider.domain.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public abstract class EventProviderBaseRequest {

    @NotBlank(message = "{validation.eventProviderRequest.tradingName.notBlank}")
    protected String tradingName;

    @NotBlank(message = "{validation.eventProviderRequest.companyName.notBlank}")
    protected String companyName;

    @NotBlank(message = "{validation.eventProviderRequest.registrationNumber.notBlank}")
    protected String registrationNumber;

    @NotBlank(message = "{validation.eventProviderRequest.description.notBlank}")
    @Size(max = 500, message = "{validation.eventProviderRequest.description.size}")
    protected String description;

    @NotEmpty(message = "{validation.eventProviderRequest.phones.notEmpty}")
    protected List<
            @NotBlank(message = "{validation.eventProviderRequest.phone.notBlank}")
            @Pattern(
                    regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$",
                    message = "{validation.eventProviderRequest.phone.pattern}"
            ) String
            > phones = new ArrayList<>();

    @NotEmpty(message = "{validation.eventProviderRequest.emails.notEmpty}")
    protected List<String> emails = new ArrayList<>();

    @NotBlank(message = "{validation.eventProviderRequest.neighborhood.notBlank}")
    protected String neighborhood;

    @NotBlank(message = "{validation.eventProviderRequest.state.notBlank}")
    @Pattern(regexp = "^[A-Za-z]{2}$", message = "{validation.eventProviderRequest.state.pattern}")
    protected String state;

    @NotBlank(message = "{validation.eventProviderRequest.city.notBlank}")
    protected String city;

    @NotBlank(message = "{validation.eventProviderRequest.zipCode.notBlank}")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "{validation.eventProviderRequest.zipCode.pattern}")
    protected String zipCode;

    @NotEmpty(message = "{validation.eventProviderRequest.paymentMethods.notEmpty}")
    protected List<PaymentMethod> paymentMethods = new ArrayList<>();

    protected List<MultipartFile> images = new ArrayList<>();

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }
}
