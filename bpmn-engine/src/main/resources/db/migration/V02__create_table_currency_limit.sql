CREATE TABLE currency_limit
(
    id       UUID         NOT NULL,
    currency VARCHAR(255) NOT NULL,
    "limit"  BIGINT       NOT NULL,
    CONSTRAINT pk_currency_limit PRIMARY KEY (id)
);
