package com.catering.app.account;

import com.catering.app.account.domain.UserAccount;
import com.catering.app.eventprovider.EventProviderRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountOnboardingService {

    private final AccountRepository accountRepository;
    private final EventProviderRepository eventProviderRepository;

    public AccountOnboardingService(AccountRepository accountRepository, EventProviderRepository eventProviderRepository) {
        this.accountRepository = accountRepository;
        this.eventProviderRepository = eventProviderRepository;
    }

    public boolean requiresEventProviderRegistration(Long accountId) {
        if (accountId == null) {
            return false;
        }

        UserAccount account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return false;
        }

        return eventProviderRepository.findByOwnerAccountId(accountId).isEmpty();
    }
}
