package com.catering.app.eventprovider;

import com.catering.app.account.AccountRepository;
import com.catering.app.account.domain.UserAccount;
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

import java.util.List;

@Service
public class EventProviderService {

    private final EventProviderMapper eventProviderMapper;
    private final EventProviderRepository eventProviderRepository;
    private final StorageService storageService;
    private final AccountRepository accountRepository;

    public EventProviderService(
            EventProviderMapper eventProviderMapper,
            EventProviderRepository eventProviderRepository,
            StorageService storageService,
            AccountRepository accountRepository
    ) {
        this.eventProviderMapper = eventProviderMapper;
        this.eventProviderRepository = eventProviderRepository;
        this.storageService = storageService;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Long create(EventProviderCreateRequest createRequest, Long ownerAccountId) {
        EventProvider newEventProvider = eventProviderMapper.createEntity(createRequest);
        assignOwnerAccount(newEventProvider, ownerAccountId);

        uploadImages(createRequest, newEventProvider);
        eventProviderRepository.save(newEventProvider);

        return newEventProvider.getId();
    }

    @Transactional
    public void update(EventProviderUpdateRequest updateRequest) {
        EventProvider existingEventProvider = eventProviderRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("Provider nao encontrado"));

        uploadImages(updateRequest, existingEventProvider);
        eventProviderMapper.updateEntity(existingEventProvider, updateRequest);
    }

    public EventProviderUpdateRequest findById(Long id) {
        EventProvider existingEventProvider = eventProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider nao encontrado"));

        return eventProviderMapper.toEventProviderUpdate(existingEventProvider);
    }

    public EventProviderUpdateRequest findByOwnerAccountId(Long ownerAccountId) {
        EventProvider existingEventProvider = eventProviderRepository.findByOwnerAccountId(ownerAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Provider nao encontrado"));

        return eventProviderMapper.toEventProviderUpdate(existingEventProvider);
    }

    public boolean hasEventProviderForAccount(Long ownerAccountId) {
        return ownerAccountId != null && eventProviderRepository.findByOwnerAccountId(ownerAccountId).isPresent();
    }

    public boolean ownsEventProvider(Long ownerAccountId, Long eventProviderId) {
        if (ownerAccountId == null || eventProviderId == null) {
            return false;
        }

        return eventProviderRepository.findByOwnerAccountId(ownerAccountId)
                .map(EventProvider::getId)
                .filter(eventProviderId::equals)
                .isPresent();
    }

    private void uploadImages(EventProviderBaseRequest eventProviderBaseRequest, EventProvider eventProvider) {
        List<MultipartFile> images = eventProviderBaseRequest.getImages();
        List<MultipartFile> validImages = storageService.filterValidImages(images);

        if (!validImages.isEmpty()) {
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

    private void assignOwnerAccount(EventProvider eventProvider, Long ownerAccountId) {
        if (ownerAccountId == null) {
            return;
        }

        UserAccount ownerAccount = accountRepository.findById(ownerAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Conta nao encontrada"));

        eventProvider.assignOwnerAccount(ownerAccount);
    }
}
