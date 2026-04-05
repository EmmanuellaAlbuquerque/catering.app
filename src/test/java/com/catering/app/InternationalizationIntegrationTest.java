package com.catering.app;

import com.catering.app.account.AccountRepository;
import com.catering.app.account.AccountSession;
import com.catering.app.account.domain.AccountType;
import com.catering.app.account.domain.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InternationalizationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    void shouldRenderHomeInPortugueseByDefault() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Language", "pt-BR"));

        assertThat(messageSource.getMessage("home.hero.title", null, Locale.forLanguageTag("pt-BR")))
                .isEqualTo("Comece criando sua conta de fornecedor de eventos.");
    }

    @Test
    void shouldRenderHomeInEnglishWhenRequested() throws Exception {
        mockMvc.perform(get("/").param("lang", "en"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Language", "en"));

        assertThat(messageSource.getMessage("home.hero.title", null, Locale.ENGLISH))
                .isEqualTo("Start by creating your event provider account.");
    }

    @Test
    void shouldRenderLoginValidationMessagesInEnglish() throws Exception {
        MvcResult result = mockMvc.perform(post("/accounts/login")
                        .param("lang", "en")
                        .param("email", "")
                        .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Language", "en"))
                .andExpect(model().attribute("messageCode", "account.login.feedback.invalidForm"))
                .andReturn();

        BindingResult bindingResult = (BindingResult) result.getModelAndView()
                .getModel()
                .get(BindingResult.MODEL_KEY_PREFIX + "accountLoginRequest");

        assertThat(bindingResult.getFieldError("email").getDefaultMessage()).isEqualTo("Enter your email.");
        assertThat(bindingResult.getFieldError("password").getDefaultMessage()).isEqualTo("Password must be between 8 and 72 characters.");
    }

    @Test
    void shouldRenderLocaleSwitcherWithPublicLoginRoute() throws Exception {
        mockMvc.perform(get("/accounts/login")
                        .param("email", "owner@buffet.com")
                        .param("password", "SenhaSegura123")
                        .param("lang", "en"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("action=\"/accounts/login\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("name=\"email\" value=\"owner@buffet.com\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.containsString("/WEB-INF/views/account/loginForm.jsp"))));
    }

    @Test
    void shouldRenderDuplicateEmailMessageInSpanish() throws Exception {
        accountRepository.save(new UserAccount("dono@buffet.com", "hash-existente", AccountType.EVENT_PROVIDER_OWNER));

        MvcResult result = mockMvc.perform(post("/accounts/sign-up/event-providers")
                        .param("lang", "es")
                        .param("email", "dono@buffet.com")
                        .param("password", "SenhaSegura123"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Language", "es"))
                .andExpect(model().attribute("messageCode", "account.signup.feedback.invalidForm"))
                .andReturn();

        BindingResult bindingResult = (BindingResult) result.getModelAndView()
                .getModel()
                .get(BindingResult.MODEL_KEY_PREFIX + "eventProviderAccountCreateRequest");

        assertThat(bindingResult.getFieldError("email").getCode()).isEqualTo("account.signup.feedback.duplicateEmail");
        assertThat(messageSource.getMessage("account.signup.feedback.duplicateEmail", null, Locale.forLanguageTag("es")))
                .isEqualTo("Ya existe una cuenta registrada con este correo.");
    }

    @Test
    void shouldRenderEventProviderPageInEnglish() throws Exception {
        UserAccount account = accountRepository.save(new UserAccount("owner@buffet.com", "hash", AccountType.EVENT_PROVIDER_OWNER));

        mockMvc.perform(get("/events/create")
                        .param("lang", "en")
                        .sessionAttr(AccountSession.ACCOUNT_ID, account.getId()))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Language", "en"))
                .andExpect(model().attributeExists("paymentMethodOptions"));

        assertThat(messageSource.getMessage("eventProvider.create.hero.title", null, Locale.ENGLISH))
                .isEqualTo("Register providers with more clarity and organization.");
        assertThat(messageSource.getMessage("common.actions.saveChanges", null, Locale.ENGLISH))
                .isEqualTo("Save changes");
    }
}
