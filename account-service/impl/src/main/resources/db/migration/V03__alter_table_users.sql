ALTER TABLE users
    ADD name VARCHAR(255);

UPDATE users
SET name = '';

ALTER TABLE users
    ALTER COLUMN name SET NOT NULL;
