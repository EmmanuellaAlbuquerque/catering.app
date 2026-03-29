package com.catering.app.account;

import com.catering.app.account.domain.AccountType;
import com.catering.app.account.domain.UserAccount;
import com.catering.app.account.request.AccountLoginRequest;
import com.catering.app.account.request.EventProviderAccountCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordHasher passwordHasher;

    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldCreateEventProviderOwnerAccount() {
        EventProviderAccountCreateRequest request = new EventProviderAccountCreateRequest();
        request.setEmail("  DONO@Buffet.com ");
        request.setPassword("SenhaSegura123");

        when(accountRepository.existsByEmail("dono@buffet.com")).thenReturn(false);
        when(passwordHasher.hash("SenhaSegura123")).thenReturn("hashed-password");
        doAnswer(invocation -> {
            UserAccount account = invocation.getArgument(0);
            ReflectionTestUtils.setField(account, "id", 15L);
            return account;
        }).when(accountRepository).save(any(UserAccount.class));

        Long createdId = accountService.createEventProviderAccount(request);

        ArgumentCaptor<UserAccount> accountCaptor = ArgumentCaptor.forClass(UserAccount.class);
        verify(accountRepository).save(accountCaptor.capture());

        UserAccount savedAccount = accountCaptor.getValue();

        assertThat(savedAccount.getEmail()).isEqualTo("dono@buffet.com");
        assertThat(savedAccount.getPasswordHash()).isEqualTo("hashed-password");
        assertThat(savedAccount.getAccountType()).isEqualTo(AccountType.EVENT_PROVIDER_OWNER);
        assertThat(createdId).isEqualTo(15L);
    }

    @Test
    void shouldRejectDuplicateEmail() {
        EventProviderAccountCreateRequest request = new EventProviderAccountCreateRequest();
        request.setEmail("dono@buffet.com");
        request.setPassword("SenhaSegura123");

        when(accountRepository.existsByEmail("dono@buffet.com")).thenReturn(true);

        assertThatThrownBy(() -> accountService.createEventProviderAccount(request))
                .isInstanceOf(DuplicateEmailException.class)
                .hasMessageContaining("e-mail");
    }

    @Test
    void shouldAuthenticateWithValidCredentials() {
        AccountLoginRequest request = new AccountLoginRequest();
        request.setEmail("Dono@Buffet.com");
        request.setPassword("SenhaSegura123");

        UserAccount account = new UserAccount("dono@buffet.com", "hashed-password", AccountType.EVENT_PROVIDER_OWNER);
        ReflectionTestUtils.setField(account, "id", 22L);

        when(accountRepository.findByEmail("dono@buffet.com")).thenReturn(java.util.Optional.of(account));
        when(passwordHasher.matches("SenhaSegura123", "hashed-password")).thenReturn(true);

        Long accountId = accountService.authenticate(request);

        assertThat(accountId).isEqualTo(22L);
    }

    @Test
    void shouldRejectInvalidCredentials() {
        AccountLoginRequest request = new AccountLoginRequest();
        request.setEmail("dono@buffet.com");
        request.setPassword("SenhaErrada123");

        UserAccount account = new UserAccount("dono@buffet.com", "hashed-password", AccountType.EVENT_PROVIDER_OWNER);
        when(accountRepository.findByEmail("dono@buffet.com")).thenReturn(java.util.Optional.of(account));
        when(passwordHasher.matches("SenhaErrada123", "hashed-password")).thenReturn(false);

        assertThatThrownBy(() -> accountService.authenticate(request))
                .isInstanceOf(InvalidCredentialsException.class)
                .hasMessageContaining("invalidos");
    }
}
