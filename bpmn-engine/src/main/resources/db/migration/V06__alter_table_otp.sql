ALTER TABLE otp
    ADD password INTEGER;

UPDATE otp
SET password = 0;

ALTER TABLE otp
    ALTER COLUMN password SET NOT NULL;
