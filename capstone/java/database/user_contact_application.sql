-- ABORT
-- select * from pets
BEGIN TRANSACTION;

DROP TABLE IF EXISTS shelter_user, contact, application, status, pets;

DROP SEQUENCE IF EXISTS seq_user_id, seq_contact_id, seq_application_id;

CREATE TABLE status (
	status_id varchar(1) NOT NULL,
	status_name varchar(8) NOT NULL,
	CONSTRAINT PK_status PRIMARY KEY (status_id)
);

-- contact_id starts at 4001 to keep the length in consistent.
CREATE SEQUENCE seq_contact_id
	INCREMENT BY 1
	START WITH 4001
	NO MAXVALUE;

CREATE TABLE contact (
	contact_id int NOT NULL DEFAULT nextval('seq_contact_id'),
	contact_name varchar(50) NOT NULL CHECK (contact_name <> ''),
	phone varchar(20),
	email varchar(128),
	city varchar(26),
	state varchar(2),
	age int NOT NULL CHECK (age <> 0),
	social_link varchar(256),
	CONSTRAINT PK_contact PRIMARY KEY (contact_id),
	CHECK ((phone IS NOT NULL AND phone <> '') OR (email IS NOT NULL AND email <> ''))
);

-- user_id starts at 1001 to keep the length in consistent.
CREATE SEQUENCE seq_user_id
	INCREMENT BY 1
	START WITH 1001
	NO MAXVALUE;
	
CREATE TABLE shelter_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	contact_id int REFERENCES contact (contact_id),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	user_role varchar(128) NOT NULL,	
	CONSTRAINT PK_shelter_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- application_id starts at 7001 to keep the length in consistent.
CREATE SEQUENCE seq_application_id
	INCREMENT BY 1
	START WITH 7001
	NO MAXVALUE;
	
CREATE TABLE application (
	application_id int NOT NULL DEFAULT nextval('seq_application_id'),
	contact_id int NOT NULL REFERENCES contact (contact_id),
	status_id varchar(1) NOT NULL REFERENCES status (status_id),
	weekly_hours int NOT NULL CHECK (weekly_hours <> 0),
	is_day boolean NOT NULL,
	preferred_animal varchar(32) NOT NULL CHECK (preferred_animal <> ''),
	reason varchar(1000) ,
	time_registered timestamp DEFAULT NOW(),		
	CONSTRAINT PK_application PRIMARY KEY (application_id)
);

CREATE TABLE pets (
	pet_id SERIAL primary key,
	pet_name varchar(50) NOT NULL,
	pet_type varchar(200) NOT NULL,
	pet_age int,
	pet_sex varchar(10) NOT NULL,
	pet_breed varchar(50) NOT NULL,
	pet_size varchar(10) NOT NULL,
	is_good_with_kids BOOLEAN,
	is_good_with_dogs BOOLEAN,
	is_good_with_cats BOOLEAN,
	is_available BOOLEAN,
	image_link varchar(200),
	user_id int NULL,
	--user_id int REFERENCES shelter_user (user_id),
	CONSTRAINT FK_user_id FOREIGN KEY(user_id) REFERENCES shelter_user(user_id)	
);

INSERT INTO status(status_id, status_name) VALUES ('A', 'Approved'), ('R', 'Rejected'), ('P', 'Pending');

INSERT INTO shelter_user(username, password_hash, user_role) VALUES ('admin', '$2a$10$B78o/ehFJ3NcDncPzZ7BwOiCD6MRQtyrP6UzWQ.f53JVLAMbCgxeG', 'ROLE_ADMIN');

INSERT INTO contact (contact_name, phone, email, city, state, age, social_link)
VALUES ('Kevin', '1234567890', 'kevinhsu16739@gmail.com', 'Columbus', 'OH', 23, '123@link');

INSERT INTO pets (pet_name, pet_type, pet_age, pet_sex, pet_breed, pet_size, is_good_with_kids, is_good_with_dogs, is_good_with_cats, is_available, image_link, user_id)
Values ('max','dog',10, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8', null),
('frank','cat',3, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1615789591457-74a63395c990', null),
('belle','dog',4, 'female', 'domestic short hair', 'XXL', true, false, false, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8', null),
('benny','dog',14, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8', null),
('tiger','cat',1, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1615789591457-74a63395c990', null),
('Deer','dog',10, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8', null),
('Doodle','cat',2, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1670471198259-137eacb1a430?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2127&q=80', null),
('Bear','dog',4, 'female', 'domestic short hair', 'XXL', true, false, false, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8', null),
('Hawk','dog',14, 'male', 'mixed', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1587402092301-725e37c70fd8', null),
('Shark','cat',1, 'female', 'domestic short hair', 'XXL', true, false, true, true, 'https://images.unsplash.com/photo-1615789591457-74a63395c990', null);

COMMIT;
