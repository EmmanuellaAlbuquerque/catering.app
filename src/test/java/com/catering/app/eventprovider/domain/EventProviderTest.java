package com.catering.app.eventprovider.domain;

import com.catering.app.eventprovider.domain.dto.AddressData;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EventProviderTest {

    @Test
    void shouldUpdatePhonesRemovingBlankValuesAndKeepingOnlyIncomingOnes() {
        EventProvider eventProvider = new EventProvider(
                "Buffet Prime",
                "Buffet Prime LTDA",
                "12.345.678/0001-90",
                "Descricao do fornecedor"
        );
        eventProvider.addPhone("(85) 99999-1111");
        eventProvider.addPhone("(85) 98888-2222");

        eventProvider.updatePhones(Set.of("(85) 97777-3333", "   "));

        assertThat(eventProvider.getPhones())
                .extracting(Phone::getNumber)
                .containsExactlyInAnyOrder("(85) 97777-3333");
    }

    @Test
    void shouldUpdateEmailsRemovingOldOnesAndIgnoringInvalidEntries() {
        EventProvider eventProvider = new EventProvider(
                "Buffet Prime",
                "Buffet Prime LTDA",
                "12.345.678/0001-90",
                "Descricao do fornecedor"
        );
        eventProvider.addEmail("contato@buffet.com");
        eventProvider.addEmail("vendas@buffet.com");

        eventProvider.updateEmails(Set.of("novo@buffet.com", ""));

        assertThat(eventProvider.getEmails())
                .extracting(Email::getEmail)
                .containsExactlyInAnyOrder("novo@buffet.com");
    }

    @Test
    void shouldUpdateExistingAddress() {
        EventProvider eventProvider = new EventProvider(
                "Buffet Prime",
                "Buffet Prime LTDA",
                "12.345.678/0001-90",
                "Descricao do fornecedor"
        );
        eventProvider.addAddress(new AddressData("Centro", "CE", "Fortaleza", "60000-000"));

        eventProvider.updateAddress(new AddressData("Aldeota", "CE", "Fortaleza", "60150-160"));

        assertThat(eventProvider.getAddresses())
                .singleElement()
                .satisfies(address -> {
                    assertThat(address.getNeighborhood()).isEqualTo("Aldeota");
                    assertThat(address.getState()).isEqualTo("CE");
                    assertThat(address.getCity()).isEqualTo("Fortaleza");
                    assertThat(address.getZipCode()).isEqualTo("60150-160");
                });
    }
}
