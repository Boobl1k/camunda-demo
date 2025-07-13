ALTER TABLE currency_exchange
    ADD request                          jsonb,
    ADD transaction_calculation_response jsonb;

UPDATE currency_exchange
SET request = '{}';

ALTER TABLE currency_exchange
    ALTER COLUMN request SET NOT NULL;

ALTER TABLE currency_exchange
    DROP COLUMN transaction_id;
