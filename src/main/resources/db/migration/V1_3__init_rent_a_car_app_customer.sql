CREATE TABLE customer
(
    customer_id     SERIAL          NOT NULL,
    name            VARCHAR(30)     NOT NULL,
    surname         VARCHAR(30)     NOT NULL,
    phone           VARCHAR(30)     NOT NULL,
    email           VARCHAR(30)     NOT NULL,
    address_id      INT                 NULL,
    PRIMARY KEY (customer_id),
    UNIQUE (email),
    CONSTRAINT fk_customer_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)

);