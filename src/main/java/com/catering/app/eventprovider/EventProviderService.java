package com.catering.app.eventprovider;

import com.catering.app.common.config.storage.StorageService;
import com.catering.app.eventprovider.domain.EventProvider;
import com.catering.app.eventprovider.request.EventProviderCreateRequest;
import com.catering.app.eventprovider.request.EventProviderMapper;
import com.catering.app.eventprovider.request.EventProviderUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

        List<MultipartFile> validImages = createRequest.getImages().stream()
                .filter(img -> img != null && !img.isEmpty() && img.getOriginalFilename() != null && !img.getOriginalFilename().isBlank())
                .toList();

        if (!validImages.isEmpty()) {
            List<String> savedImages = storageService.store(createRequest.getImages());
            savedImages.forEach(newEventProvider::addImage);
        }

        eventProviderRepository.save(newEventProvider);

        return newEventProvider.getId();
    }

    @Transactional
    public void update(EventProviderUpdateRequest updateRequest) {
        EventProvider existingEventProvider = eventProviderRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("Provider não encontrado"));

        List<MultipartFile> validImages = updateRequest.getImages().stream()
                .filter(img -> img != null && !img.isEmpty() && img.getOriginalFilename() != null && !img.getOriginalFilename().isBlank())
                .toList();

        if(!validImages.isEmpty()) {
            List<String> savedImages = storageService.store(updateRequest.getImages());
            savedImages.forEach(imgPath -> {
                boolean alreadyExist = existingEventProvider.getImages()
                        .stream()
                        .anyMatch(img -> img.getFileName().equals(imgPath));

                if (!alreadyExist) {
                    existingEventProvider.addImage(imgPath);
                }
            });
        }

        eventProviderMapper.updateEntity(existingEventProvider, updateRequest);
    }

    public EventProviderUpdateRequest findById(Long id) {
        EventProvider existingEventProvider = eventProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider não encontrado"));

        return eventProviderMapper.toEventProviderUpdate(existingEventProvider);
    }
}
