INSERT INTO exchange_rate (id, source_currency, target_currency, exchange_rate)
VALUES (gen_random_uuid(), 'RUB', 'USD', 0.012),
       (gen_random_uuid(), 'USD', 'RUB', 79),
       (gen_random_uuid(), 'RUB', 'EUR', 0.011),
       (gen_random_uuid(), 'EUR', 'RUB', 89),
       (gen_random_uuid(), 'RUB', 'CNY', 0.085),
       (gen_random_uuid(), 'CNY', 'RUB', 11.14),
       (gen_random_uuid(), 'CNY', 'USD', 0.137),
       (gen_random_uuid(), 'USD', 'CNY', 7.18);
