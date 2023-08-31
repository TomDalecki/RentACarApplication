CREATE TABLE rental_order
(
    rental_order_id         SERIAL                      NOT NULL,
    rental_number           VARCHAR(36)                 NOT NULL,
    received_date_time      TIMESTAMP WITH TIME ZONE    NOT NULL,
    rental_start_date       DATE                        NOT NULL,
    rental_end_date         DATE                        NOT NULL,
    total_price             NUMERIC(8, 2)               NOT NULL,
    order_status            VARCHAR(30)                 NOT NULL,
    customer_id             INT                         NOT NULL,
    car_to_rent_id          INT                         NOT NULL,
    employee_id             INT,
    PRIMARY KEY (rental_order_id),
    UNIQUE (rental_number),
    CONSTRAINT fk_rental_order_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (customer_id),
    CONSTRAINT fk_rental_order_car_to_rent
        FOREIGN KEY (car_to_rent_id)
            REFERENCES car_to_rent (car_to_rent_id),
    CONSTRAINT fk_rental_order_employee
        FOREIGN KEY (employee_id)
            REFERENCES employee (employee_id)
);