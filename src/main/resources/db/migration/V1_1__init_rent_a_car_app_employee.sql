CREATE TABLE employee
(
    employee_id SERIAL      NOT NULL,
    name        VARCHAR(30) NOT NULL,
    surname     VARCHAR(30) NOT NULL,
    pesel       VARCHAR(30) NOT NULL,
    PRIMARY KEY (employee_id),
    UNIQUE (pesel)
);

ALTER SEQUENCE employee_employee_id_seq RESTART WITH 100;