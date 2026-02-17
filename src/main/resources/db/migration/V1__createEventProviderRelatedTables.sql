CREATE TABLE IF NOT EXISTS event_provider
(
    id                  BIGINT       NOT NULL AUTO_INCREMENT UNIQUE,
    trading_name        VARCHAR(255) NOT NULL,
    company_name        VARCHAR(255) NOT NULL,
    registration_number VARCHAR(255) NOT NULL,
    description         VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS phone
(
    number            VARCHAR(255) NOT NULL,
    event_provider_id BIGINT       NOT NULL,
    PRIMARY KEY (event_provider_id, number),
    CONSTRAINT fk_phone_event_provider FOREIGN KEY (event_provider_id) REFERENCES event_provider (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS email
(
    email             VARCHAR(255) NOT NULL,
    event_provider_id BIGINT       NOT NULL,
    PRIMARY KEY (event_provider_id, email),
    CONSTRAINT fk_email_event_provider FOREIGN KEY (event_provider_id) REFERENCES event_provider (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS address
(
    id                BIGINT       NOT NULL AUTO_INCREMENT UNIQUE,
    event_provider_id BIGINT       NOT NULL,
    neighborhood      VARCHAR(255) NOT NULL,
    state             VARCHAR(255) NOT NULL,
    city              VARCHAR(255) NOT NULL,
    zipcode           VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_address_event_provider FOREIGN KEY (event_provider_id) REFERENCES event_provider (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS payment_method
(
    event_provider_id BIGINT       NOT NULL,
    payment_method    VARCHAR(255) NOT NULL,
    PRIMARY KEY (event_provider_id, payment_method),
    CONSTRAINT fk_payment_method_event_provider FOREIGN KEY (event_provider_id) REFERENCES event_provider (id) ON DELETE CASCADE
);