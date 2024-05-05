--liquibase formatted sql

--changeset foretruff:1
ALTER TABLE users
    ADD COLUMN image VARCHAR(64);

--changeset foretruff:2
ALTER TABLE users_aud
    ADD COLUMN image VARCHAR(64);
