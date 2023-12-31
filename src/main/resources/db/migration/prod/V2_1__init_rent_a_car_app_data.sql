insert into EMPLOYEE (name, surname, pesel, email)
values
('Andrzej', 'Pracownik_1', '65104561458', 'empl1@pl'),
('Wojtek', 'Pracownik_2', '78052468945', 'empl2@pl'),
('Tomasz', 'Pracownik_3', '62021258518', 'empl3@pl'),
('Łuksz', 'Pracownik_4', '88072945852', 'empl4@pl');

insert into CUSTOMER (customer_id, name, surname, phone, email)
values
('1', 'Piotr', 'Wynajmujący_1', '603604605', 'piotr@pl'),
('2', 'Jarosław', 'Wynajmujący_2', '525868985', 'jarek@pl'),
('3', 'Karol', 'Wynajmujący_3', '236589456', 'karol@pl'),
('4', 'Justyna', 'Wynajmujący_4', '996995993', 'justyna@pl');

insert into CAR_TO_RENT (car_to_rent_id, vin, car_id_number, car_type, brand, model, production_year, color, car_status)
values
('1', '1FT7X2B60FEA74019', 'SK12345', 'Sedan', 'Fiat', 'Punto', '2014', 'grey', 'TO_RENT'),
('2', '1N6BD06T45C416702', 'SK67890', 'Cabriolet', 'Hyundai', 'i30', '2020', 'red', 'TECHNICAL_ISSUE'),
('3', '1G1PE5S97B7239380', 'SK78945', 'SUV', 'Ford', 'Kuga', '2019', 'black', 'TO_RENT'),
('4', '1GCEC19X27Z109567', 'SK89456', 'Sedan', 'Skoda', 'Fabia', '2016', 'white', 'DISABLED_BY_INSURANCE'),
('5', '2C3CDYAG2DH731952', 'SK32165', 'Sedan', 'Toyota', 'Corolla', '2022', 'white', 'TO_RENT'),
('6', '1GB6G5CG1C1105936', 'SK98765', 'SUV', 'Dacia', 'Duster', '2021', 'black', 'DISABLED_BY_TECH_INSP');


insert into CAR_INSURANCE (insurance_company, insurance_type, insurance_number, insurance_start_date, insurance_end_date, car_to_rent_id)
values
('Warta', 'OC', 'OC123456/1', '20200123', '20230501', '1'),
('Warta', 'OC', 'OC234567/2', '20200123', '202340501', '2'),
('Warta', 'OC', 'OC345678/6', '20200123', '20230501', '3'),
('Warta', 'OC', 'OC456789/4', '20200123', '20230501', '4'),
('Warta', 'OC', 'OC567890/7', '20200123', '20230501', '5'),
('Warta', 'OC', 'OC678901/9', '20200123', '20230501', '6'),
('PZU', 'AC', 'AC123456/0', '20200123', '20230501', '1'),
('PZU', 'AC', 'AC234567/2', '20200123', '20230501', '2'),
('PZU', 'AC', 'AC345618/9', '20200123', '20230501', '3'),
('PZU', 'AC', 'AC456123/1', '20200123', '20230501', '4'),
('PZU', 'AC', 'AC598763/6', '20200123', '20230501', '5'),
('PZU', 'AC', 'AC654321/8', '20200123', '20230501', '6');

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
