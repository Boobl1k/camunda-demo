CREATE TABLE transaction
(
    id                UUID         NOT NULL,
    user_id           UUID         NOT NULL,
    source_account_id UUID         NOT NULL,
    source_amount     BIGINT       NOT NULL,
    source_currency   VARCHAR(255) NOT NULL,
    target_account_id UUID         NOT NULL,
    target_amount     BIGINT       NOT NULL,
    target_currency   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);
