CREATE TABLE exchange_rate
(
    id              UUID         NOT NULL,
    source_currency VARCHAR(255) NOT NULL,
    target_currency VARCHAR(255) NOT NULL,
    exchange_rate   DECIMAL      NOT NULL,
    CONSTRAINT pk_exchange_rate PRIMARY KEY (id)
);
