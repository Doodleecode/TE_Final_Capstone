DROP TABLE IF EXISTS pets;

CREATE TABLE pets (
	pet_id SERIAL primary key,
	pet_name varchar(50) NOT NULL,
	pet_type varchar(200) NOT NULL,
	pet_age int,
	is_available BOOLEAN,
	image_link varchar(200)
);

INSERT INTO pets (pet_name, pet_type, pet_age, is_available, image_link)
Values ('max','dog',10,true,'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('frank','cat',3,true,'https://images.unsplash.com/photo-1615789591457-74a63395c990'),
('belle','dog',4,false,'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('benny','dog',14,true,'https://images.unsplash.com/photo-1587402092301-725e37c70fd8'),
('tiger','cat',1,true,'https://images.unsplash.com/photo-1615789591457-74a63395c990');
