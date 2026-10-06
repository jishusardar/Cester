CREATE TABLE LLM_BLOCKED_WORDS(
    id BIGINT NOT NULL AUTO_INCREMENT,
    Word VARCHAR(200) NOT NULL ,
    Is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL ,
    updated_at DATETIME NOT NULL ,
    deleted_at DATETIME,
    CONSTRAINT pk_llm_blocked_words PRIMARY KEY (id),
    CONSTRAINT uk_llm_blocked_words UNIQUE (Word)
);