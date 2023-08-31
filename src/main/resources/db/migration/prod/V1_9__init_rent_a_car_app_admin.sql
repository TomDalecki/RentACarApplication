CREATE TABLE admin
(
    admin_id    SERIAL      NOT NULL,
    name        VARCHAR(30) NOT NULL,
    surname     VARCHAR(30) NOT NULL,
    pesel       VARCHAR(30) NOT NULL,
    email       VARCHAR(30) NOT NULL,
    PRIMARY KEY (admin_id),
    UNIQUE (pesel, email)
);

ALTER SEQUENCE admin_admin_id_seq RESTART WITH 100;