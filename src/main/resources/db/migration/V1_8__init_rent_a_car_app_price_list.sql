CREATE TABLE price_list
(
    price_id    SERIAL      NOT NULL,
    price       NUMERIC(8, 2) NOT NULL,
    price_date  TIMESTAMP WITH TIME ZONE NOT NULL,
    car_type    VARCHAR(30) NOT NULL,
    PRIMARY KEY (price_id)
);