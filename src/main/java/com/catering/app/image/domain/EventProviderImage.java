package com.catering.app.image.domain;

import com.catering.app.eventprovider.domain.EventProvider;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EVENT_PROVIDER")
public class EventProviderImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    private EventProvider eventProvider;

    @Deprecated
    protected EventProviderImage() {}

    public EventProviderImage(String fileName, EventProvider eventProvider) {
        super(fileName);
        this.eventProvider = eventProvider;
    }
}
