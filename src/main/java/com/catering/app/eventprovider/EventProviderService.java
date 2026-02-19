package com.catering.app.eventprovider;

import com.catering.app.common.config.storage.StorageService;
import com.catering.app.eventprovider.domain.EventProvider;
import com.catering.app.eventprovider.request.EventProviderBaseRequest;
import com.catering.app.eventprovider.request.EventProviderCreateRequest;
import com.catering.app.eventprovider.request.EventProviderMapper;
import com.catering.app.eventprovider.request.EventProviderUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class EventProviderService {

    private final EventProviderMapper eventProviderMapper;
    private final EventProviderRepository eventProviderRepository;
    private final StorageService storageService;

    public EventProviderService(EventProviderMapper eventProviderMapper, EventProviderRepository eventProviderRepository, StorageService storageService) {
        this.eventProviderMapper = eventProviderMapper;
        this.eventProviderRepository = eventProviderRepository;
        this.storageService = storageService;
    }

    @Transactional
    public Long create(EventProviderCreateRequest createRequest) {
        EventProvider newEventProvider = eventProviderMapper.createEntity(createRequest);

        uploadImages(createRequest, newEventProvider);
        eventProviderRepository.save(newEventProvider);

        return newEventProvider.getId();
    }

    @Transactional
    public void update(EventProviderUpdateRequest updateRequest) {
        EventProvider existingEventProvider = eventProviderRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("Provider não encontrado"));

        uploadImages(updateRequest, existingEventProvider);
        eventProviderMapper.updateEntity(existingEventProvider, updateRequest);
    }

    public EventProviderUpdateRequest findById(Long id) {
        EventProvider existingEventProvider = eventProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider não encontrado"));

        return eventProviderMapper.toEventProviderUpdate(existingEventProvider);
    }

    private void uploadImages(EventProviderBaseRequest eventProviderBaseRequest, EventProvider eventProvider) {
        List<MultipartFile> images = eventProviderBaseRequest.getImages();
        List<MultipartFile> validImages = storageService.filterValidImages(images);

        if(!validImages.isEmpty()) {
            List<String> savedImages = storageService.store(validImages);

            savedImages.forEach(imgPath -> {
                boolean imgAlreadyExist = eventProvider.getImages()
                        .stream()
                        .anyMatch(img -> img.getFileName().equals(imgPath));

                if (!imgAlreadyExist) {
                    eventProvider.addImage(imgPath);
                }
            });
        }
    }
}
