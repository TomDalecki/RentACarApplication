CREATE TABLE car_to_rent
(
    car_to_rent_id  SERIAL         NOT NULL,
    vin             VARCHAR(30)    NOT NULL,
    car_id_number   VARCHAR(30)    NOT NULL,
    car_type        VARCHAR(30)    NOT NULL,
    brand           VARCHAR(30)    NOT NULL,
    model           VARCHAR(30)    NOT NULL,
    production_year SMALLINT       NOT NULL,
    color           VARCHAR(30)    NOT NULL,
    car_status      VARCHAR(30)    NOT NULL,

    PRIMARY KEY (car_to_rent_id),
    UNIQUE (vin, car_id_number)
);

ALTER SEQUENCE car_to_rent_car_to_rent_id_seq RESTART WITH 100;