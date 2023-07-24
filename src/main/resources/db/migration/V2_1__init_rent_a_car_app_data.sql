insert into SALESMAN (name, surname, pesel)
values
('Andrzej', 'Sprzedawca_1', '65104561458'),
('Wojtek', 'Sprzedawca_2', '78052468945'),
('Tomasz', 'Sprzedawca_3', '62021258518'),
('Łuksz', 'Sprzwedawca_4', '88072945852');

insert into CUSTOMER (customer_id, name, surname, phone, email)
values
('1', 'Piotr', 'Wynajmujący_1', '603604605', 'piotr@com.pl'),
('2', 'Jarosław', 'Wynajmujący_2', '525868985', 'jarek@com.pl'),
('3', 'Karol', 'Wynajmujący_3', '236589456', 'karol@com.pl'),
('4', 'Justyna', 'Wynajmujący_4', '996995993', 'justyna@com.pl');

insert into CAR_TO_RENT (car_to_rent_id, vin, car_id_number, car_type, brand, model, production_year, color, car_status)
values
('1', '1FT7X2B60FEA74019', 'SK12345', 'Sedan', 'Fiat', 'Punto', '2020', 'grey', 'TO_RENT'),
('2', '1N6BD06T45C416702', 'SK67890', 'Sedan', 'Hyundai', 'i30', '2020', 'red', 'TO_RENT'),
('3', '1G1PE5S97B7239380', 'SK78945', 'Sedan', 'Ford', 'Kuga', '2020', 'black', 'TO_RENT'),
('4', '1GCEC19X27Z109567', 'SK89456', 'Sedan', 'Skoda', 'Fabia', '2020', 'white', 'TO_RENT'),
('5', '2C3CDYAG2DH731952', 'SK32165', 'Sedan', 'Toyota', 'Corolla', '2020', 'white', 'TO_RENT'),
('6', '1GB6G5CG1C1105936', 'SK98765', 'Sedan', 'Dacia', 'Duster', '2020', 'black', 'TO_RENT');


insert into CAR_INSURANCE (insurance_company, insurance_type, insurance_number, insurance_end_date, insurance_start_date, car_to_rent_id)
values
('Warta', 'OC', 'OC123456/1', '20200123', '20240501', '1'),
('Warta', 'OC', 'OC234567/2', '20200123', '20240501', '2'),
('Warta', 'OC', 'OC345678/6', '20200123', '20240501', '3'),
('Warta', 'OC', 'OC456789/4', '20200123', '20240501', '4'),
('Warta', 'OC', 'OC567890/7', '20200123', '20240501', '5'),
('Warta', 'OC', 'OC678901/9', '20200123', '20240501', '6'),
('PZU', 'AC', 'AC123456/0', '20200123', '20240501', '1'),
('PZU', 'AC', 'AC234567/2', '20200123', '20240501', '2'),
('PZU', 'AC', 'AC345618/9', '20200123', '20240501', '3'),
('PZU', 'AC', 'AC456123/1', '20200123', '20240501', '4'),
('PZU', 'AC', 'AC598763/6', '20200123', '20240501', '5'),
('PZU', 'AC', 'AC654321/8', '20200123', '20240501', '6');

insert into CAR_TECHNICAL_INSPECTION (inspection_expiry_date, car_to_rent_id)
values
('20240501', '1'),
('20240501', '2'),
('20240501', '3'),
('20240501', '4'),
('20240501', '5'),
('20240501', '6');

insert into price_list (price, price_date, car_type)
values
('100.00', '20200501', 'Sedan'),
('150.00', '20200501', 'Combi'),
('200.00', '20200501', 'Cabriolet'),
('250.00', '20200501', 'SUV');
