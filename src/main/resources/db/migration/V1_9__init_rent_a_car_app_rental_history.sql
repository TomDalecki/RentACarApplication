CREATE TABLE rental_history
(
    rental_history_id   SERIAL                      NOT NULL,
    rental_start_date   DATE                        NOT NULL,
    rental_end_date     DATE                        NOT NULL,
    total_price         NUMERIC(8, 2)               NOT NULL,
    rent_order_status   VARCHAR(30)                 NOT NULL,
    car_to_rent_id      INT                         NOT NULL,
    rental_order_id     INT                         NOT NULL,
    PRIMARY KEY (rental_history_id),
    CONSTRAINT fk_rental_history_car_to_rent
        FOREIGN KEY (car_to_rent_id)
            REFERENCES car_to_rent (car_to_rent_id),
    CONSTRAINT fk_rental_history_rental_order
        FOREIGN KEY (rental_order_id)
            REFERENCES rental_order (rental_order_id)
);