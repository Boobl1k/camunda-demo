INSERT INTO currency_limit (id, currency, "limit")
VALUES (gen_random_uuid(), 'USD', 100000),
       (gen_random_uuid(), 'EUR', 100000),
       (gen_random_uuid(), 'RUB', 10000000),
       (gen_random_uuid(), 'CNY', 20000);
