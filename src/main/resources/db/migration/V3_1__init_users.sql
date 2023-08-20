ALTER TABLE admin
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES security_user (user_id);

ALTER TABLE customer
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES security_user (user_id);

ALTER TABLE employee
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES security_user (user_id);

insert into security_user (user_id, email, password, active) values (1, 'admin1@email.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);

insert into security_user (user_id, email, password, active) values (2, 'piotr@com.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into security_user (user_id, email, password, active) values (3, 'jarek@com.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into security_user (user_id, email, password, active) values (4, 'karol@com.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into security_user (user_id, email, password, active) values (5, 'justyna@com.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);

insert into security_user (user_id, email, password, active) values (6, 'Pracownik_1@email.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into security_user (user_id, email, password, active) values (7, 'Pracownik_2@email.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into security_user (user_id, email, password, active) values (8, 'Pracownik_3@email.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into security_user (user_id, email, password, active) values (9, 'Pracownik_4@email.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);

UPDATE admin SET user_id = 1 WHERE email = 'admin1@email.pl';

UPDATE customer SET user_id = 2 WHERE email = 'piotr@com.pl';
UPDATE customer SET user_id = 3 WHERE email = 'jarek@com.pl';
UPDATE customer SET user_id = 4 WHERE email = 'karol@com.pl';
UPDATE customer SET user_id = 5 WHERE email = 'justyna@com.pl';

UPDATE employee SET user_id = 6 WHERE email = 'Pracownik_1@email.pl';
UPDATE employee SET user_id = 7 WHERE email = 'Pracownik_2@email.pl';
UPDATE employee SET user_id = 8 WHERE email = 'Pracownik_3@email.pl';
UPDATE employee SET user_id = 9 WHERE email = 'Pracownik_4@email.pl';


insert into security_role (role_id, role) values (1, 'ADMIN'), (2, 'EMPLOYEE'), (3, 'USER');

insert into security_user_role (user_id, role_id) values (1, 1);
insert into security_user_role (user_id, role_id) values (2, 3), (3, 3), (4, 3), (5, 3);
insert into security_user_role (user_id, role_id) values (6, 2), (7, 2), (8, 2), (9, 2);

ALTER TABLE customer
ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE employee
ALTER COLUMN user_id SET NOT NULL;

