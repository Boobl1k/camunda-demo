ALTER TABLE currency_exchange
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

UPDATE currency_exchange
SET created_at = now();

ALTER TABLE currency_exchange
    ALTER COLUMN created_at SET NOT NULL;
