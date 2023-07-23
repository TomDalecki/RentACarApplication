CREATE TABLE salesman
(
    salesman_id SERIAL      NOT NULL,
    name        VARCHAR(30) NOT NULL,
    surname     VARCHAR(30) NOT NULL,
    pesel       VARCHAR(30) NOT NULL,
    PRIMARY KEY (salesman_id),
    UNIQUE (pesel)
);