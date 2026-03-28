package com.catering.app.eventprovider;

import com.catering.app.eventprovider.domain.EventProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventProviderRepository extends JpaRepository<EventProvider, Long> {

    Optional<EventProvider> findByOwnerAccountId(Long ownerAccountId);
}
