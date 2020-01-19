CREATE TABLE IF NOT EXISTS cuser
(
     id SERIAL PRIMARY KEY NOT NULL,
     firstName varchar(100),
     lastName varchar (100),
     email varchar (100),
     username varchar (150),
     pw varchar(100)
);
