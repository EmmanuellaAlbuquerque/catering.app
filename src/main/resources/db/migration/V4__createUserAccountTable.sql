CREATE TABLE IF NOT EXISTS user_account
(
    id            BIGINT       NOT NULL AUTO_INCREMENT UNIQUE,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(512) NOT NULL,
    account_type  VARCHAR(64)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_user_account_email UNIQUE (email)
);
