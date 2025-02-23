CREATE TABLE insurance_providers
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    description    TEXT,
    license_number VARCHAR(50) UNIQUE,
    contact_email  VARCHAR(255),
    contact_phone  VARCHAR(50),
    created_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_active      BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE insurance_quotes
(
    id                BIGSERIAL PRIMARY KEY,
    coverage_type     VARCHAR(50)    NOT NULL,
    price             DECIMAL(19, 2) NOT NULL,
    provider_id       BIGINT         NOT NULL,
    deductible_amount DECIMAL(19, 2),
    coverage_limit    DECIMAL(19, 2),
    coverage_start_date        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    coverage_end_date        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    terms             TEXT,
    created_at        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quote_status      VARCHAR(50)    NOT NULL DEFAULT 'DRAFT',
    CONSTRAINT fk_provider FOREIGN KEY (provider_id) REFERENCES insurance_providers (id)
);