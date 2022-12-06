BEGIN TRANSACTION;

DROP TABLE IF EXISTS shelter_user, contact, application, status;

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
	contact_name varchar(50) NOT NULL,
	phone varchar(10),
	email varchar(50),
	city varchar(20) NOT NULL,
	state varchar(2) NOT NULL,
	age int NOT NULL,
	social_link varchar(50),
	CONSTRAINT PK_contact PRIMARY KEY (contact_id)	
);

-- user_id starts at 1001 to keep the length in consistent.
CREATE SEQUENCE seq_user_id
	INCREMENT BY 1
	START WITH 1001
	NO MAXVALUE;
	
CREATE TABLE shelter_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	contact_id int NOT NULL REFERENCES contact (contact_id),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	user_role varchar(10) NOT NULL,	
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
	weekly_hours int NOT NULL,
	is_day boolean NOT NULL,
	prefered_animal varchar(10) NOT NULL,
	reason varchar(1000) NOT NULL,
	time_registered timestamp DEFAULT NOW(),		
	CONSTRAINT PK_application PRIMARY KEY (application_id)
);

INSERT INTO status(status_id, status_name) VALUES ('A', 'Approved'), ('R', 'Rejected'), ('P', 'Pending');

COMMIT;
