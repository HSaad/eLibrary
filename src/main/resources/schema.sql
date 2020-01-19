CREATE TABLE IF NOT EXISTS cuser
(
     id SERIAL PRIMARY KEY NOT NULL,
     first varchar(100),
     last varchar (100),
     email varchar (100),
     company varchar (150),
     city varchar(100)
);
