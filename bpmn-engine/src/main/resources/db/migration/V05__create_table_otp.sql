CREATE TABLE otp
(
    id      UUID    NOT NULL,
    user_id UUID    NOT NULL,
    solved  BOOLEAN NOT NULL,
    CONSTRAINT pk_otp PRIMARY KEY (id)
);
