package com.catering.app.account;

import com.catering.app.account.domain.AccountType;
import com.catering.app.account.domain.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    void shouldCreateOwnerAccountThroughHttpFlow() throws Exception {
        MvcResult result = mockMvc.perform(post("/accounts/sign-up/event-providers")
                        .param("email", "Dono@Buffet.com")
                        .param("password", "SenhaSegura123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events/create"))
                .andReturn();

        List<UserAccount> savedAccounts = accountRepository.findAll();

        assertThat(savedAccounts).hasSize(1);

        UserAccount savedAccount = savedAccounts.getFirst();

        assertThat(savedAccount.getEmail()).isEqualTo("dono@buffet.com");
        assertThat(savedAccount.getAccountType()).isEqualTo(AccountType.EVENT_PROVIDER_OWNER);
        assertThat(savedAccount.getPasswordHash())
                .isNotBlank()
                .isNotEqualTo("SenhaSegura123");
        assertThat(result.getRequest().getSession(false)).isNotNull();
        assertThat(result.getRequest().getSession(false).getAttribute(AccountSession.ACCOUNT_ID)).isEqualTo(savedAccount.getId());
    }

    @Test
    void shouldRejectDuplicateEmailThroughHttpFlow() throws Exception {
        accountRepository.save(new UserAccount("dono@buffet.com", "hash-existente", AccountType.EVENT_PROVIDER_OWNER));

        mockMvc.perform(post("/accounts/sign-up/event-providers")
                        .param("email", "dono@buffet.com")
                        .param("password", "SenhaSegura123"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/eventProviderSignUpForm"))
                .andExpect(model().attributeHasFieldErrors("eventProviderAccountCreateRequest", "email"));

        assertThat(accountRepository.findAll()).hasSize(1);
    }

    @Test
    void shouldRedirectLoggedAccountWithoutProviderToCreatePage() throws Exception {
        UserAccount account = accountRepository.save(new UserAccount("dono@buffet.com", "hash-existente", AccountType.EVENT_PROVIDER_OWNER));

        mockMvc.perform(get("/")
                        .sessionAttr(AccountSession.ACCOUNT_ID, account.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events/create"));
    }
}
