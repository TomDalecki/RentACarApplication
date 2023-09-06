CREATE TABLE security_user
(
    user_id   SERIAL        NOT NULL,
    email     VARCHAR(32)   NOT NULL,
    password  VARCHAR(128)  NOT NULL,
    active    BOOLEAN       NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email)
);

ALTER SEQUENCE security_user_user_id_seq RESTART WITH 100;

CREATE TABLE security_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

ALTER SEQUENCE security_role_role_id_seq RESTART WITH 100;

CREATE TABLE security_user_role
(
    user_id   INT      NOT NULL,
    role_id   INT      NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_security_user_role_user
        FOREIGN KEY (user_id)
            REFERENCES security_user (user_id),
    CONSTRAINT fk_security_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES security_role (role_id)
);
