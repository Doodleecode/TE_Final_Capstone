DROP TABLE IF EXISTS pets;

CREATE TABLE pets (
	pet_id SERIAL primary key,
	pet_name varchar(50) NOT NULL,
	pet_type varchar(200) NOT NULL,
	pet_age int,
	is_avalible BOOLEAN,
	image_link varchar(200)
);

insert into pets (pet_name, pet_type, pet_age, is_avalible)
Values ('max','dog',10,true);

insert into pets (pet_name, pet_type, pet_age, is_avalible)
Values ('frank','cat',3,true);

insert into pets (pet_name, pet_type, pet_age, is_avalible)
Values ('belle','dog',4,false);


insert into pets (pet_name, pet_type, pet_age, is_avalible)
Values ('benny','dog',14,true);

insert into pets (pet_name, pet_type, pet_age, is_avalible)
Values ('tiger','cat',1,true)