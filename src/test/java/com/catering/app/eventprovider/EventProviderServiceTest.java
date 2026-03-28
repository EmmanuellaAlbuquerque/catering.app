package com.catering.app.eventprovider;

import com.catering.app.account.AccountRepository;
import com.catering.app.account.domain.AccountType;
import com.catering.app.account.domain.UserAccount;
import com.catering.app.common.config.storage.StorageService;
import com.catering.app.eventprovider.domain.EventProvider;
import com.catering.app.eventprovider.domain.dto.AddressData;
import com.catering.app.eventprovider.request.EventProviderCreateRequest;
import com.catering.app.eventprovider.request.EventProviderMapper;
import com.catering.app.eventprovider.request.EventProviderUpdateRequest;
import com.catering.app.image.domain.Image;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventProviderServiceTest {

    @Mock
    private EventProviderMapper eventProviderMapper;

    @Mock
    private EventProviderRepository eventProviderRepository;

    @Mock
    private StorageService storageService;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private EventProviderService eventProviderService;

    @Test
    void shouldCreateEventProviderAndReturnGeneratedId() {
        EventProviderCreateRequest createRequest = new EventProviderCreateRequest();
        createRequest.setImages(List.of());

        EventProvider eventProvider = new EventProvider(
                "Atelier Gourmet",
                "Atelier Gourmet LTDA",
                "12.345.678/0001-90",
                "Descricao"
        );
        ReflectionTestUtils.setField(eventProvider, "id", 10L);

        when(eventProviderMapper.createEntity(createRequest)).thenReturn(eventProvider);
        when(storageService.filterValidImages(createRequest.getImages())).thenReturn(List.of());

        Long createdId = eventProviderService.create(createRequest, null);

        assertThat(createdId).isEqualTo(10L);
        verify(eventProviderMapper).createEntity(createRequest);
        verify(storageService).filterValidImages(createRequest.getImages());
        verify(eventProviderRepository).save(eventProvider);
        verify(storageService, never()).store(any(List.class));
    }

    @Test
    void shouldUploadOnlyNewImagesDuringCreate() {
        EventProviderCreateRequest createRequest = new EventProviderCreateRequest();
        MultipartFile image = org.mockito.Mockito.mock(MultipartFile.class);
        createRequest.setImages(List.of(image));

        EventProvider eventProvider = new EventProvider(
                "Atelier Gourmet",
                "Atelier Gourmet LTDA",
                "12.345.678/0001-90",
                "Descricao"
        );
        ReflectionTestUtils.setField(eventProvider, "id", 11L);
        eventProvider.addImage("foto-existente.jpg");

        when(eventProviderMapper.createEntity(createRequest)).thenReturn(eventProvider);
        when(storageService.filterValidImages(createRequest.getImages())).thenReturn(List.of(image));
        when(storageService.store(List.of(image))).thenReturn(List.of("foto-existente.jpg", "foto-nova.jpg"));

        Long createdId = eventProviderService.create(createRequest, null);

        assertThat(createdId).isEqualTo(11L);
        assertThat(eventProvider.getImages())
                .extracting(Image::getFileName)
                .containsExactlyInAnyOrder("foto-existente.jpg", "foto-nova.jpg");
        verify(storageService).store(List.of(image));
        verify(eventProviderRepository).save(eventProvider);
    }

    @Test
    void shouldUpdateExistingEventProvider() {
        EventProviderUpdateRequest updateRequest = new EventProviderUpdateRequest(
                7L,
                "Buffet Atualizado",
                "Buffet Atualizado LTDA",
                "98.765.432/0001-10",
                "Descricao atualizada",
                List.of("(85) 97777-2222"),
                List.of("novo@buffet.com"),
                "Aldeota",
                "CE",
                "Fortaleza",
                "60150-160",
                List.of()
        );
        updateRequest.setImages(List.of());

        EventProvider existingProvider = new EventProvider(
                "Buffet Original",
                "Buffet Original LTDA",
                "98.765.432/0001-10",
                "Descricao original"
        );
        existingProvider.addAddress(new AddressData("Centro", "CE", "Fortaleza", "60000-000"));

        when(eventProviderRepository.findById(7L)).thenReturn(Optional.of(existingProvider));
        when(storageService.filterValidImages(updateRequest.getImages())).thenReturn(List.of());
        doNothing().when(eventProviderMapper).updateEntity(existingProvider, updateRequest);

        eventProviderService.update(updateRequest);

        verify(eventProviderRepository).findById(7L);
        verify(storageService).filterValidImages(updateRequest.getImages());
        verify(eventProviderMapper).updateEntity(existingProvider, updateRequest);
        verify(storageService, never()).store(any(List.class));
    }

    @Test
    void shouldAssignOwnerAccountWhenCreatingEventProvider() {
        EventProviderCreateRequest createRequest = new EventProviderCreateRequest();
        createRequest.setImages(List.of());

        EventProvider eventProvider = new EventProvider(
                "Atelier Gourmet",
                "Atelier Gourmet LTDA",
                "12.345.678/0001-90",
                "Descricao"
        );
        ReflectionTestUtils.setField(eventProvider, "id", 18L);

        UserAccount ownerAccount = new UserAccount("dono@buffet.com", "hash", AccountType.EVENT_PROVIDER_OWNER);

        when(eventProviderMapper.createEntity(createRequest)).thenReturn(eventProvider);
        when(storageService.filterValidImages(createRequest.getImages())).thenReturn(List.of());
        when(accountRepository.findById(9L)).thenReturn(Optional.of(ownerAccount));

        Long createdId = eventProviderService.create(createRequest, 9L);

        assertThat(createdId).isEqualTo(18L);
        assertThat(eventProvider.getOwnerAccount()).isSameAs(ownerAccount);
        verify(accountRepository).findById(9L);
        verify(eventProviderRepository).save(eventProvider);
    }

    @Test
    void shouldThrowWhenUpdatingMissingEventProvider() {
        EventProviderUpdateRequest updateRequest = new EventProviderUpdateRequest(
                99L,
                "Buffet Atualizado",
                "Buffet Atualizado LTDA",
                "98.765.432/0001-10",
                "Descricao atualizada",
                List.of("(85) 97777-2222"),
                List.of("novo@buffet.com"),
                "Aldeota",
                "CE",
                "Fortaleza",
                "60150-160",
                List.of()
        );

        when(eventProviderRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> eventProviderService.update(updateRequest))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Provider");

        verify(eventProviderRepository).findById(99L);
        verify(eventProviderMapper, never()).updateEntity(any(), any());
    }

    @Test
    void shouldReturnMappedUpdateRequestWhenFindingById() {
        EventProvider eventProvider = new EventProvider(
                "Atelier Gourmet",
                "Atelier Gourmet LTDA",
                "12.345.678/0001-90",
                "Descricao"
        );
        EventProviderUpdateRequest mappedRequest = new EventProviderUpdateRequest(
                5L,
                "Atelier Gourmet",
                "Atelier Gourmet LTDA",
                "12.345.678/0001-90",
                "Descricao",
                List.of("(85) 99999-9999"),
                List.of("contato@atelier.com"),
                "Meireles",
                "CE",
                "Fortaleza",
                "60165-090",
                List.of()
        );

        when(eventProviderRepository.findById(5L)).thenReturn(Optional.of(eventProvider));
        when(eventProviderMapper.toEventProviderUpdate(eventProvider)).thenReturn(mappedRequest);

        EventProviderUpdateRequest result = eventProviderService.findById(5L);

        assertThat(result).isSameAs(mappedRequest);
        verify(eventProviderRepository).findById(5L);
        verify(eventProviderMapper).toEventProviderUpdate(eventProvider);
    }

    @Test
    void shouldThrowWhenFindingByIdForMissingEventProvider() {
        when(eventProviderRepository.findById(404L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> eventProviderService.findById(404L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Provider");

        verify(eventProviderRepository).findById(404L);
        verify(eventProviderMapper, never()).toEventProviderUpdate(any());
    }
}
