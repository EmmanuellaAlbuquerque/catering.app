package com.catering.app.eventprovider;

import com.catering.app.common.config.storage.StorageService;
import com.catering.app.eventprovider.domain.EventProvider;
import com.catering.app.eventprovider.domain.dto.AddressData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class EventProviderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventProviderRepository eventProviderRepository;

    @BeforeEach
    void setUp() {
        eventProviderRepository.deleteAll();
    }

    @Test
    void shouldCreateEventProviderThroughHttpFlow() throws Exception {
        MvcResult result = mockMvc.perform(multipart("/events/create")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("tradingName", "Atelier Gourmet")
                        .param("companyName", "Atelier Gourmet LTDA")
                        .param("registrationNumber", "12.345.678/0001-90")
                        .param("description", "Buffet especializado em casamentos e eventos corporativos.")
                        .param("phones[0]", "(85) 99999-9999")
                        .param("emails[0]", "contato@atelier.com")
                        .param("neighborhood", "Meireles")
                        .param("state", "CE")
                        .param("city", "Fortaleza")
                        .param("zipCode", "60165-090"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        List<EventProvider> savedProviders = eventProviderRepository.findAll();

        assertThat(savedProviders).hasSize(1);

        EventProvider savedProvider = savedProviders.getFirst();

        assertThat(result.getResponse().getRedirectedUrl()).isEqualTo("/events/edit/" + savedProvider.getId());
        assertThat(savedProvider.getTradingName()).isEqualTo("Atelier Gourmet");
        assertThat(savedProvider.getCompanyName()).isEqualTo("Atelier Gourmet LTDA");
        assertThat(savedProvider.getRegistrationNumber()).isEqualTo("12.345.678/0001-90");
        assertThat(savedProvider.getDescription()).isEqualTo("Buffet especializado em casamentos e eventos corporativos.");
        assertThat(savedProvider.getPhones())
                .extracting(phone -> phone.getNumber())
                .containsExactlyInAnyOrder("(85) 99999-9999");
        assertThat(savedProvider.getEmails())
                .extracting(email -> email.getEmail())
                .containsExactlyInAnyOrder("contato@atelier.com");
        assertThat(savedProvider.getAddresses())
                .singleElement()
                .satisfies(address -> {
                    assertThat(address.getNeighborhood()).isEqualTo("Meireles");
                    assertThat(address.getState()).isEqualTo("CE");
                    assertThat(address.getCity()).isEqualTo("Fortaleza");
                    assertThat(address.getZipCode()).isEqualTo("60165-090");
                });
    }

    @Test
    void shouldEditEventProviderThroughHttpFlow() throws Exception {
        EventProvider existingProvider = new EventProvider(
                "Buffet Original",
                "Buffet Original LTDA",
                "98.765.432/0001-10",
                "Descricao inicial do fornecedor."
        );
        existingProvider.addPhone("(85) 98888-1111");
        existingProvider.addEmail("original@buffet.com");
        existingProvider.addAddress(new AddressData("Centro", "CE", "Fortaleza", "60000-000"));
        eventProviderRepository.save(existingProvider);

        mockMvc.perform(multipart("/events/edit")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("id", existingProvider.getId().toString())
                        .param("tradingName", "Buffet Atualizado")
                        .param("companyName", "Buffet Atualizado LTDA")
                        .param("registrationNumber", "98.765.432/0001-10")
                        .param("description", "Fornecedor atualizado com foco em eventos sociais e corporativos.")
                        .param("phones[0]", "(85) 97777-2222")
                        .param("emails[0]", "novo@buffet.com")
                        .param("neighborhood", "Aldeota")
                        .param("state", "CE")
                        .param("city", "Fortaleza")
                        .param("zipCode", "60150-160"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events/edit/" + existingProvider.getId()));

        EventProvider updatedProvider = eventProviderRepository.findById(existingProvider.getId()).orElseThrow();

        assertThat(updatedProvider.getTradingName()).isEqualTo("Buffet Atualizado");
        assertThat(updatedProvider.getCompanyName()).isEqualTo("Buffet Atualizado LTDA");
        assertThat(updatedProvider.getDescription()).isEqualTo("Fornecedor atualizado com foco em eventos sociais e corporativos.");
        assertThat(updatedProvider.getPhones())
                .extracting(phone -> phone.getNumber())
                .containsExactlyInAnyOrder("(85) 97777-2222");
        assertThat(updatedProvider.getEmails())
                .extracting(email -> email.getEmail())
                .containsExactlyInAnyOrder("novo@buffet.com");
        assertThat(updatedProvider.getAddresses())
                .singleElement()
                .satisfies(address -> {
                    assertThat(address.getNeighborhood()).isEqualTo("Aldeota");
                    assertThat(address.getState()).isEqualTo("CE");
                    assertThat(address.getCity()).isEqualTo("Fortaleza");
                    assertThat(address.getZipCode()).isEqualTo("60150-160");
                });
    }

    @TestConfiguration
    static class TestStorageConfiguration {

        @Bean
        @Primary
        StorageService storageService() {
            return new StorageService() {
                @Override
                public void init() {
                }

                @Override
                public void store(MultipartFile file) {
                }

                @Override
                public List<String> store(List<MultipartFile> file) {
                    return Collections.emptyList();
                }

                @Override
                public Stream<Path> loadAll() {
                    return Stream.empty();
                }

                @Override
                public Path load(String filename) {
                    return Path.of(filename);
                }

                @Override
                public Resource loadAsResource(String filename) {
                    return new ByteArrayResource(new byte[0]);
                }

                @Override
                public void deleteAll() {
                }

                @Override
                public List<MultipartFile> filterValidImages(List<MultipartFile> images) {
                    return Collections.emptyList();
                }
            };
        }
    }
}
