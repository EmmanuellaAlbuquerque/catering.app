package com.catering.app.account;

import com.catering.app.account.domain.AccountType;
import com.catering.app.account.domain.UserAccount;
import com.catering.app.account.request.EventProviderAccountCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordHasher passwordHasher;

    public AccountService(AccountRepository accountRepository, PasswordHasher passwordHasher) {
        this.accountRepository = accountRepository;
        this.passwordHasher = passwordHasher;
    }

    @Transactional
    public Long createEventProviderAccount(EventProviderAccountCreateRequest request) {
        String normalizedEmail = UserAccount.normalizeEmail(request.getEmail());

        if (accountRepository.existsByEmail(normalizedEmail)) {
            throw new DuplicateEmailException("Ja existe uma conta cadastrada com este e-mail.");
        }

        UserAccount account = new UserAccount(
                normalizedEmail,
                passwordHasher.hash(request.getPassword()),
                AccountType.EVENT_PROVIDER_OWNER
        );

        accountRepository.save(account);

        return account.getId();
    }
}
