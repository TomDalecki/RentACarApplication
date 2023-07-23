CREATE TABLE car_technical_inspection
(
    car_technical_inspection_id SERIAL                      NOT NULL,
    inspection_expiry_date      TIMESTAMP WITH TIME ZONE    NOT NULL,
    car_to_rent_id              INT                         NOT NULL,
    PRIMARY KEY (car_technical_inspection_id),
    CONSTRAINT fk_car_technical_inspection_car_to_rent
        FOREIGN KEY (car_to_rent_id)
            REFERENCES car_to_rent (car_to_rent_id)
);