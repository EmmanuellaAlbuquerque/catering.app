ALTER TABLE event_provider
    ADD COLUMN owner_account_id BIGINT NULL;

ALTER TABLE event_provider
    ADD CONSTRAINT uk_event_provider_owner_account UNIQUE (owner_account_id);

ALTER TABLE event_provider
    ADD CONSTRAINT fk_event_provider_owner_account
        FOREIGN KEY (owner_account_id) REFERENCES user_account (id);
