package com.catering.app.eventprovider.request;

import com.catering.app.eventprovider.domain.Address;
import com.catering.app.eventprovider.domain.Email;
import com.catering.app.eventprovider.domain.EventProvider;
import com.catering.app.eventprovider.domain.Phone;
import com.catering.app.eventprovider.domain.dto.AddressData;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class EventProviderMapper {

    public EventProvider createEntity(EventProviderCreateRequest createRequest) {
        EventProvider eventProvider = new EventProvider(
                createRequest.getTradingName(),
                createRequest.getCompanyName(),
                createRequest.getRegistrationNumber(),
                createRequest.getDescription()
        );

        createRequest.getPhones().forEach(eventProvider::addPhone);
        createRequest.getEmails().forEach(eventProvider::addEmail);
        AddressData addressData  = new AddressData(
                createRequest.getNeighborhood(),
                createRequest.getState(),
                createRequest.getCity(),
                createRequest.getZipCode()
        );
        eventProvider.addAddress(addressData);

        return eventProvider;
    }

    public void updateEntity(EventProvider eventProvider, EventProviderUpdateRequest updateRequest) {
        eventProvider.update(
                updateRequest.getTradingName(),
                updateRequest.getCompanyName(),
                updateRequest.getRegistrationNumber(),
                updateRequest.getDescription()
        );
        eventProvider.updatePhones(new HashSet<>(updateRequest.getPhones()));
        eventProvider.updateEmails(new HashSet<>(updateRequest.getEmails()));
        AddressData addressData  = new AddressData(
                updateRequest.getNeighborhood(),
                updateRequest.getState(),
                updateRequest.getCity(),
                updateRequest.getZipCode()
        );
        eventProvider.updateAddress(addressData);
    }

    public EventProviderUpdateRequest toEventProviderUpdate(EventProvider eventProvider) {
        Address address = eventProvider.getAddresses().getFirst();

        return new EventProviderUpdateRequest(
                eventProvider.getId(),
                eventProvider.getTradingName(),
                eventProvider.getCompanyName(),
                eventProvider.getRegistrationNumber(),
                eventProvider.getDescription(),
                eventProvider.getPhones().stream().map(Phone::getNumber).toList(),
                eventProvider.getEmails().stream().map(Email::getEmail).toList(),
                address.getNeighborhood(),
                address.getState(),
                address.getCity(),
                address.getZipCode()
        );
    }
}
