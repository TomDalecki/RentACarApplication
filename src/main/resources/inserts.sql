insert into RENTAL_ORDER (rental_number, received_date_time, rental_start_date, rental_end_date, total_price, order_status, customer_id, car_to_rent_id, employee_id)
values
('1', '20230725', '20230725', '20230726', '500.00', 'TEST', '1', '1', '100');



insert into RENTAL_HISTORY (rental_start_date, rental_end_date, total_price, rent_order_status, car_to_rent_id, rental_order_id)
values
('20230725', '20230726', '500.00', 'RENTED', '1', '2');