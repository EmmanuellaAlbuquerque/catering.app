package com.catering.app.eventprovider.request;

import com.catering.app.eventprovider.domain.EventProvider;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventProviderMapperTest {

    private final EventProviderMapper mapper = new EventProviderMapper();

    @Test
    void shouldCreateEntityFromCreateRequest() {
        EventProviderCreateRequest request = new EventProviderCreateRequest();
        request.setTradingName("Atelier Gourmet");
        request.setCompanyName("Atelier Gourmet LTDA");
        request.setRegistrationNumber("12.345.678/0001-90");
        request.setDescription("Buffet para eventos sociais.");
        request.setPhones(List.of("(85) 99999-9999"));
        request.setEmails(List.of("contato@atelier.com"));
        request.setNeighborhood("Meireles");
        request.setState("CE");
        request.setCity("Fortaleza");
        request.setZipCode("60165-090");

        EventProvider entity = mapper.createEntity(request);

        assertThat(entity.getTradingName()).isEqualTo("Atelier Gourmet");
        assertThat(entity.getCompanyName()).isEqualTo("Atelier Gourmet LTDA");
        assertThat(entity.getRegistrationNumber()).isEqualTo("12.345.678/0001-90");
        assertThat(entity.getDescription()).isEqualTo("Buffet para eventos sociais.");
        assertThat(entity.getPhones())
                .extracting(phone -> phone.getNumber())
                .containsExactlyInAnyOrder("(85) 99999-9999");
        assertThat(entity.getEmails())
                .extracting(email -> email.getEmail())
                .containsExactlyInAnyOrder("contato@atelier.com");
        assertThat(entity.getAddresses())
                .singleElement()
                .satisfies(address -> {
                    assertThat(address.getNeighborhood()).isEqualTo("Meireles");
                    assertThat(address.getState()).isEqualTo("CE");
                    assertThat(address.getCity()).isEqualTo("Fortaleza");
                    assertThat(address.getZipCode()).isEqualTo("60165-090");
                });
    }

    @Test
    void shouldMapEntityToUpdateRequest() {
        EventProvider eventProvider = new EventProvider(
                "Atelier Gourmet",
                "Atelier Gourmet LTDA",
                "12.345.678/0001-90",
                "Buffet para eventos sociais."
        );
        ReflectionTestUtils.setField(eventProvider, "id", 7L);
        eventProvider.addPhone("(85) 99999-9999");
        eventProvider.addEmail("contato@atelier.com");
        eventProvider.addAddress(new com.catering.app.eventprovider.domain.dto.AddressData("Meireles", "CE", "Fortaleza", "60165-090"));
        eventProvider.addImage("foto-1.jpg");
        eventProvider.addImage("foto-2.jpg");

        EventProviderUpdateRequest updateRequest = mapper.toEventProviderUpdate(eventProvider);

        assertThat(updateRequest.getId()).isEqualTo(7L);
        assertThat(updateRequest.getTradingName()).isEqualTo("Atelier Gourmet");
        assertThat(updateRequest.getCompanyName()).isEqualTo("Atelier Gourmet LTDA");
        assertThat(updateRequest.getRegistrationNumber()).isEqualTo("12.345.678/0001-90");
        assertThat(updateRequest.getDescription()).isEqualTo("Buffet para eventos sociais.");
        assertThat(updateRequest.getPhones()).containsExactly("(85) 99999-9999");
        assertThat(updateRequest.getEmails()).containsExactly("contato@atelier.com");
        assertThat(updateRequest.getNeighborhood()).isEqualTo("Meireles");
        assertThat(updateRequest.getState()).isEqualTo("CE");
        assertThat(updateRequest.getCity()).isEqualTo("Fortaleza");
        assertThat(updateRequest.getZipCode()).isEqualTo("60165-090");
        assertThat(updateRequest.getUrlImages()).containsExactlyInAnyOrder("foto-1.jpg", "foto-2.jpg");
    }
}
