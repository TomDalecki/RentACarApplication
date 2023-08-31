ALTER TABLE admin
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES security_user (user_id);

ALTER TABLE customer
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES security_user (user_id);

ALTER TABLE employee
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES security_user (user_id);

insert into security_user (user_id, email, password, active) values (1, 'admin1@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);

insert into security_user (user_id, email, password, active) values (2, 'piotr@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);
insert into security_user (user_id, email, password, active) values (3, 'jarek@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);
insert into security_user (user_id, email, password, active) values (4, 'karol@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);
insert into security_user (user_id, email, password, active) values (5, 'justyna@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);

insert into security_user (user_id, email, password, active) values (6, 'empl1@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);
insert into security_user (user_id, email, password, active) values (7, 'empl2@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);
insert into security_user (user_id, email, password, active) values (8, 'empl3@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);
insert into security_user (user_id, email, password, active) values (9, 'empl4@pl', '$2a$12$KydimTK1vbeHFaRaajS3yOOJwGz.9claV6NT3MNFi1Z2rHRQoxhde', true);

UPDATE admin SET user_id = 1 WHERE email = 'admin1@pl';

UPDATE customer SET user_id = 2 WHERE email = 'piotr@pl';
UPDATE customer SET user_id = 3 WHERE email = 'jarek@pl';
UPDATE customer SET user_id = 4 WHERE email = 'karol@pl';
UPDATE customer SET user_id = 5 WHERE email = 'justyna@pl';

UPDATE employee SET user_id = 6 WHERE email = 'empl1@pl';
UPDATE employee SET user_id = 7 WHERE email = 'empl2@pl';
UPDATE employee SET user_id = 8 WHERE email = 'empl3@pl';
UPDATE employee SET user_id = 9 WHERE email = 'empl4@pl';


insert into security_role (role_id, role) values (1, 'ADMIN'), (2, 'EMPLOYEE'), (3, 'USER');

insert into security_user_role (user_id, role_id) values (1, 1), (1, 2), (1, 3);
insert into security_user_role (user_id, role_id) values (2, 3), (3, 3), (4, 3), (5, 3);
insert into security_user_role (user_id, role_id) values (6, 2), (7, 2), (8, 2), (9, 2);

ALTER TABLE customer
ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE employee
ALTER COLUMN user_id SET NOT NULL;

