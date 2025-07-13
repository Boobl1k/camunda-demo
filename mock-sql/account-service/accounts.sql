INSERT INTO account(id, user_id, currency, amount)
VALUES (gen_random_uuid(), 'af74deda-b91e-4cde-8e40-0bf7eadb1d26', 'RUB', 12000000),
       (gen_random_uuid(), 'af74deda-b91e-4cde-8e40-0bf7eadb1d26', 'USD', 1000),
       (gen_random_uuid(), 'af74deda-b91e-4cde-8e40-0bf7eadb1d26', 'EUR', 0),
       (gen_random_uuid(), '3fd94d65-3410-41cd-8510-327725db4233', 'RUB', 0),
       (gen_random_uuid(), '3fd94d65-3410-41cd-8510-327725db4233', 'CNY', 1000000),
       (gen_random_uuid(), 'aa008117-6f28-4cdb-b936-b0af1de9bc6f', 'RUB', 1000000000000),
       (gen_random_uuid(), 'aa008117-6f28-4cdb-b936-b0af1de9bc6f', 'USD', 0);
