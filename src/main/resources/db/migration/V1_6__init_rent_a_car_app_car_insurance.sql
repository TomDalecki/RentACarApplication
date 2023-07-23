CREATE TABLE car_insurance
(
    car_insurance_id        SERIAL                      NOT NULL,
    insurance_company       VARCHAR(30)                 NOT NULL,
    insurance_type          VARCHAR(30)                 NOT NULL,
    insurance_number        VARCHAR(30)                 NOT NULL,
    insurance_end_date    TIMESTAMP WITH TIME ZONE    NOT NULL,
    insurance_start_date    TIMESTAMP WITH TIME ZONE    NOT NULL,
    car_to_rent_id          INT                         NOT NULL,
    PRIMARY KEY (car_insurance_id),
    UNIQUE (insurance_number),
    CONSTRAINT fk_car_insurance_car_to_rent
        FOREIGN KEY (car_to_rent_id)
            REFERENCES car_to_rent (car_to_rent_id)
);