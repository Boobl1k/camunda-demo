CREATE TABLE currency_exchange
(
    id             UUID     NOT NULL,
    transaction_id UUID,
    status         SMALLINT NOT NULL,
    error_reason   VARCHAR(255),
    CONSTRAINT pk_currency_exchange PRIMARY KEY (id)
);
