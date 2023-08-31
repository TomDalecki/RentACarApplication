CREATE TABLE address
(
    address_id  SERIAL      NOT NULL,
    country     VARCHAR(30) NOT NULL,
    city        VARCHAR(30) NOT NULL,
    postal_code VARCHAR(30) NOT NULL,
    address     VARCHAR(100) NOT NULL,
    PRIMARY KEY (address_id)
);