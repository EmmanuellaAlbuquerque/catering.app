package com.catering.app.eventprovider.domain;

import com.catering.app.eventprovider.domain.dto.AddressData;
import com.catering.app.image.domain.EventProviderImage;
import jakarta.persistence.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.catering.app.common.util.StringUtils.isValid;
import static com.catering.app.common.util.StringUtils.sanitizeList;

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

    @ElementCollection
    @CollectionTable(
            name = "phone",
            joinColumns = @JoinColumn(name = "event_provider_id")
    )
    private final Set<Phone> phones = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "email",
            joinColumns = @JoinColumn(name = "event_provider_id")
    )
    private final Set<Email> emails = new HashSet<>();

    @OneToMany(mappedBy = "eventProvider", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "payment_method",
            joinColumns = @JoinColumn(name = "event_provider_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private final Set<PaymentMethod> paymentMethods = new HashSet<>();

    @OneToMany(mappedBy = "eventProvider", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<EventProviderImage> images = new ArrayList<>();

    @Deprecated
    public EventProvider() {}

    public EventProvider(String tradingName, String companyName, String registrationNumber, String description) {
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.description = description;
    }

    public void update(String tradingName, String companyName, String registrationNumber, String description) {
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
        this.description = description;
    }

    public void addPhone(String phoneNumber) {
        if(isValid(phoneNumber)) {
            Phone phone = new Phone(phoneNumber);
            this.phones.add(phone);
        }
    }

    public void updatePhones(Set<String> rawNumbers) {
        Set<String> incomingNumbers = sanitizeList(rawNumbers);
        synchronizeCollection(this.phones, incomingNumbers, Phone::getNumber, this::addPhone);
    }

    public void addEmail(String newEmail) {
        if(isValid(newEmail)) {
            Email email = new Email(newEmail);
            this.emails.add(email);
        }
    }

    public void updateEmails(Set<String> rawEmails) {
        Set<String> incomingEmails = sanitizeList(rawEmails);
        synchronizeCollection(this.emails, incomingEmails, Email::getEmail, this::addEmail);
    }

    public void addAddress(AddressData addressData){
        Address address = new Address(addressData.neighborhood(),
                addressData.state(),
                addressData.city(),
                addressData.zipCode(),
                this
        );
        this.addresses.add(address);
    }

    public void updateAddress(AddressData addressData) {
        if (!this.addresses.isEmpty()) {
            Address address = this.addresses.getFirst();
            address.update(addressData.neighborhood(), addressData.state(), addressData.city(), addressData.zipCode());
        }
    }

    public void addImage(String filename) {
        EventProviderImage image = new EventProviderImage(filename, this);
        this.images.add(image);
    }

    public Long getId() {
        return id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getDescription() {
        return description;
    }

    public Set<Phone> getPhones() {
        return Collections.unmodifiableSet(this.phones);
    }

    public Set<Email> getEmails() {
        return Collections.unmodifiableSet(emails);
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public Set<PaymentMethod> getPaymentMethods() {
        return Collections.unmodifiableSet(paymentMethods);
    }

    public List<EventProviderImage> getImages() {
        return Collections.unmodifiableList(this.images);
    }

    private <T, V> void synchronizeCollection(Collection<T> currentCollection, Set<V> incomingValues, Function<T, V> valueExtractor, Consumer<V> adder) {
        currentCollection.removeIf(item -> !incomingValues.contains(valueExtractor.apply(item)));

        for (T item : currentCollection) {
            incomingValues.remove(valueExtractor.apply(item));
        }

        incomingValues.forEach(adder);
    }
}
