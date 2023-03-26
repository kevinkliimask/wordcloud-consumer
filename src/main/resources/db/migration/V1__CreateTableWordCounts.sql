CREATE TABLE word_counts
(
    id          BIGSERIAL PRIMARY KEY,
    message_id  UUID NOT NULL,
    word_counts OID  NOT NULL,
    created_at  DATE NOT NULL
);
