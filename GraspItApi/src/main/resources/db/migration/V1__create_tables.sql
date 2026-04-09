CREATE TABLE cards
(
    id       UUID NOT NULL,
    deck_id  UUID,
    question VARCHAR(255),
    answer   VARCHAR(255),
    CONSTRAINT pk_cards PRIMARY KEY (id)
);

CREATE TABLE decks
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    user_id     UUID,
    CONSTRAINT pk_decks PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       UUID NOT NULL,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE cards
    ADD CONSTRAINT FK_CARDS_ON_DECK FOREIGN KEY (deck_id) REFERENCES decks (id);

ALTER TABLE decks
    ADD CONSTRAINT FK_DECKS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);