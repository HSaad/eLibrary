INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Admin', 'John', 'Smith', 'john.smith@gmail.com', 'jsmith', '123');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Admin', 'Jane', 'Smith', 'jane.smith@gmail.com', 'janesmith', '1234');
INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size) VALUES('Book', 'Harry Potter', 'JK Rowling', 'Harry yer a wizard', 'Fantasy', 1999, 'true', 150);

INSERT INTO loan(item_id, user_id) VALUES(1, 1);
