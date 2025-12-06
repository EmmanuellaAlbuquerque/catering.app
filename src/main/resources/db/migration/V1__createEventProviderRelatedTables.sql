CREATE TABLE IF NOT EXISTS event_provider
(
    id                  INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    trading_name        VARCHAR(255) NOT NULL,
    company_name        VARCHAR(255) NOT NULL,
    registration_number VARCHAR(255) NOT NULL,
    description         VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS phone
(
    id                INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    number            VARCHAR(255) NOT NULL,
    event_provider_id INTEGER      NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS email
(
    id                INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    email             VARCHAR(255) NOT NULL,
    event_provider_id INTEGER      NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS address
(
    id                INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    event_provider_id INTEGER      NOT NULL UNIQUE,
    neighborhood      VARCHAR(255) NOT NULL,
    state             VARCHAR(255) NOT NULL,
    city              VARCHAR(255) NOT NULL,
    zipcode           VARCHAR(255) NOT NULL,
    PRIMARY KEY (id, event_provider_id)
);


CREATE TABLE IF NOT EXISTS payment_method
(
    id                INTEGER      NOT NULL AUTO_INCREMENT UNIQUE,
    name              VARCHAR(255) NOT NULL,
    event_provider_id INTEGER      NOT NULL,
    PRIMARY KEY (id)
);
