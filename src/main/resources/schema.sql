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
