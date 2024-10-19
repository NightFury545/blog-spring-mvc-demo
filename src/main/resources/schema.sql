CREATE TABLE IF NOT EXISTS users
(
    id       VARCHAR(128) PRIMARY KEY NOT NULL UNIQUE ,
    username VARCHAR(24)              NOT NULL UNIQUE,
    password VARCHAR(32)              NOT NULL
);

CREATE TABLE IF NOT EXISTS posts
(
    id      VARCHAR(128) PRIMARY KEY NOT NULL UNIQUE,
    title   VARCHAR(24)              NOT NULL UNIQUE,
    content VARCHAR(7240)            NOT NULL,
    slug    VARCHAR(24)              NOT NULL,
    user_id VARCHAR(128)             NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)