CREATE TABLE account
(
    id       UUID         NOT NULL,
    user_id  UUID         NOT NULL,
    currency VARCHAR(255) NOT NULL,
    amount   BIGINT       NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
