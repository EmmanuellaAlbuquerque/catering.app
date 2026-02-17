package com.catering.app.eventprovider;

import com.catering.app.eventprovider.domain.EventProvider;
import com.catering.app.eventprovider.request.EventProviderCreateRequest;
import com.catering.app.eventprovider.request.EventProviderMapper;
import com.catering.app.eventprovider.request.EventProviderUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventProviderService {

    private final EventProviderMapper eventProviderMapper;
    private final EventProviderRepository eventProviderRepository;

    public EventProviderService(EventProviderMapper eventProviderMapper, EventProviderRepository eventProviderRepository) {
        this.eventProviderMapper = eventProviderMapper;
        this.eventProviderRepository = eventProviderRepository;
    }

    public Long create(EventProviderCreateRequest createRequest) {
        EventProvider newEventProvider = eventProviderMapper.createEntity(createRequest);
        eventProviderRepository.save(newEventProvider);
        return newEventProvider.getId();
    }

    @Transactional
    public void update(EventProviderUpdateRequest updateRequest) {
        EventProvider existingEventProvider = eventProviderRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("Provider não encontrado"));

        eventProviderMapper.updateEntity(existingEventProvider, updateRequest);
    }

    public EventProviderUpdateRequest findById(Long id) {
        EventProvider existingEventProvider = eventProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider não encontrado"));

        return eventProviderMapper.toEventProviderUpdate(existingEventProvider);
    }
}
