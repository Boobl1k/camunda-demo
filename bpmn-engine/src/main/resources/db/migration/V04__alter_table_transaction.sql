ALTER TABLE transaction
    ADD successful BOOLEAN;

UPDATE transaction
SET successful = true;

ALTER TABLE transaction
    ALTER COLUMN successful SET NOT NULL;
