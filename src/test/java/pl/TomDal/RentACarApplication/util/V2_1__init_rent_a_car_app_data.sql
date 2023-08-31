insert into EMPLOYEE (name, surname, pesel, email)
values
('Andrzej', 'Pracownik_1', '65104561458', 'empl1@pl'),
('Wojtek', 'Pracownik_2', '78052468945', 'empl2@pl');

insert into CUSTOMER (customer_id, name, surname, phone, email)
values
('1', 'Jarosław', 'Wynajmujący_2', '525868985', 'jarek@pl'),
('2', 'Justyna', 'Wynajmujący_4', '996995993', 'justyna@pl');

insert into CAR_TO_RENT (car_to_rent_id, vin, car_id_number, car_type, brand, model, production_year, color, car_status)
values
('1', '1GCEC19X27Z109567', 'SK89456', 'Sedan', 'Skoda', 'Fabia', '2016', 'white', 'TO_RENT'),
('2', '2C3CDYAG2DH731952', 'SK32165', 'Sedan', 'Toyota', 'Corolla', '2022', 'white', 'TO_RENT'),
('3', '1GB6G5CG1C1105936', 'SK98765', 'SUV', 'Dacia', 'Duster', '2021', 'black', 'TO_RENT');

insert into price_list (price, price_date, car_type)
values
('100.00', '20200501', 'Sedan'),
('150.00', '20200501', 'Combi'),
('200.00', '20200501', 'Cabriolet'),
('250.00', '20200501', 'SUV');
