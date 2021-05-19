DROP TABLE IF EXISTS make CASCADE;
DROP TABLE IF EXISTS model CASCADE;
DROP TABLE IF EXISTS listing CASCADE;
DROP TABLE IF EXISTS image CASCADE;

--------------/ [TABLES] /--------------

CREATE TABLE IF NOT EXISTS make (
    name varchar(255) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS model (
	name varchar(255) PRIMARY KEY,
	make_name varchar not null references make(name) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS listing (
	id serial PRIMARY KEY,
	seller_id varchar(255) NOT NULL,
	title VARCHAR(100) NOT NULL,
	description VARCHAR(255) NOT NULL,
	price NUMERIC NOT NULL,
	make_name varchar(255) NOT NULL,
	model_name varchar(255) NOT NULL,
	CONSTRAINT fk_make_name FOREIGN KEY (make_name) REFERENCES make (name) ON DELETE CASCADE,
	CONSTRAINT fk_model_name FOREIGN KEY (model_name) REFERENCES model (name) ON DELETE CASCADE,
	model_year int NOT null,
	created_on timestamp NOT NULL
);

CREATE TABLE IMAGE (
	id serial PRIMARY KEY,
	name varchar(255) NOT NULL,
	img bytea NOT NULL
);



--------------/ [PROCEDURES] /--------------
-- INSERT MAKE PROCEDURE --
CREATE OR REPLACE PROCEDURE INSERT_MAKE (name VARCHAR(255))
LANGUAGE plpgsql
AS $$
BEGIN  
  INSERT INTO MAKE (name) VALUES (name);  
END $$;

-- INSERT MODEL PROCEDURE --
CREATE OR REPLACE PROCEDURE INSERT_MODEL (
	name VARCHAR(255),
	make_name VARCHAR(255)
)
LANGUAGE plpgsql
AS $$
BEGIN  
  INSERT INTO MODEL (name, make_name) VALUES (name, make_name);  
END $$;

-- INSERT MAKE AND MODEL PROCEDURE --
CREATE OR REPLACE PROCEDURE INSERT_MAKE_AND_MODEL (
	make_name varchar(255),
	model_name varchar(255)
)
LANGUAGE plpgsql
AS $$
BEGIN
	INSERT INTO MAKE(name) VALUES (make_name);
	INSERT INTO MODEL (name, make_name) VALUES (model_name, make_name);  
END; $$;



-- INSERT LISTING PROCEDURE --
CREATE OR REPLACE PROCEDURE INSERT_LISTING (
	seller_id varchar(255),
	title VARCHAR(100),
	description VARCHAR(255),
	price NUMERIC,
	make_name varchar(255),
	model_name varchar(255),
	model_year int
)
LANGUAGE plpgsql
AS $$
BEGIN  
  
INSERT INTO listing(seller_id, title, description, price, make_name, model_name, model_year, created_on) VALUES (seller_id, title, description, price, make_name, model_name, model_year, NOW());
END $$;

-- FIND ALL LISTINGS BY CUSTOMER ID --
CREATE OR REPLACE FUNCTION FIND_ALL_LISTINGS_BY_SELLER_ID(
	_id varchar(255))
  returns setof listing
as
$$
select * from listing where seller_id = _id;
$$
language sql;



-- INSERT MODEL PROCEDURE --
CREATE OR REPLACE PROCEDURE INSERT_MODEL (
	name VARCHAR(255),
	make_name VARCHAR(255)
)
LANGUAGE plpgsql
AS $$
BEGIN  
  INSERT INTO MODEL (name, make_name) VALUES (name, make_name);  
END $$;


--------------/ [VIEWS] /--------------
CREATE OR REPLACE VIEW ALL_LISTINGS AS SELECT * FROM LISTING;



--------------/ [POPULATE] /--------------

CALL INSERT_MAKE('Mercedes');
CALL INSERT_MAKE('Volvo');
CALL INSERT_MODEL('A1', 'Mercedes');
CALL INSERT_MODEL('A2', 'Mercedes');
CALL INSERT_MODEL('A3', 'Mercedes');
CALL INSERT_MODEL('X1', 'Volvo');
CALL INSERT_MODEL('C1', 'Volvo');

CALL INSERT_LISTING('60a3d5347f39c4bfa873c580', 'Brand New Mercedes A1 from 2021', 'This is a brand new Mercedes A1 that i bought in early 2021, but im selling it cuz im a stupid cunt', 420000, 'Mercedes', 'A1', 2021);
CALL INSERT_LISTING('60a3d5347f39c4bfa873c580', 'Used Volvo A1 C1 2018', 'This is a used volvo from 2018', 26000, 'Volvo', 'C1', 2018);
CALL INSERT_LISTING('60a3d5347f39c4bfa873c589', 'Volvo X1', 'Selling my old volvo x1 cheap', 4700, 'Volvo', 'X1', 2004);


select * from FIND_ALL_LISTINGS_BY_SELLER_ID('60a3d5347f39c4bfa873c580')
