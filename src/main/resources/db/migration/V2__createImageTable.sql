CREATE TABLE image
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    filename   VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    entity_type VARCHAR(50)  NOT NULL,
    entity_id   BIGINT,
    INDEX       idx_image_entity (entity_id, entity_type)
);
