CREATE TABLE IF NOT EXISTS cuser
(
     id SERIAL PRIMARY KEY NOT NULL,
     user_type varchar(20),
     first_name varchar(100),
     last_name varchar (100),
     email varchar (100),
     username varchar (150),
     pw varchar(100),
     privilege varchar(100)
);

CREATE TABLE IF NOT EXISTS libitem
(
     id SERIAL PRIMARY KEY NOT NULL,
     item_type varchar(20),
     title varchar(100),
     creator varchar (100),
     description varchar (150),
     date_added date,
     genre varchar(100),
     publication_year int,
     img_src varchar(150),
     available boolean
);
