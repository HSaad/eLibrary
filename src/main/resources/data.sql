INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'John', 'Smith', 'john.smith@gmail.com', 'jsmith', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'Jane', 'Smith', 'jane.smith@gmail.com', 'janesmith', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'Hagr', 'Saad', 'hagr@gmail.com', 'hsaad', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'Johnathan', 'Smith', 'jonsmith@gmail.com', 'jonathan', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Admin', 'Hagr', 'Saad', 'hagrsaad@gmail.com', 'hagr', '12345');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Librarian', 'Joe', 'Brown', 'helloworld@gmail.com', 'joe', '12345');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Librarian', 'Jack', 'Brown', 'jack.brown@gmail.com', 'jack', '12345');


INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size) VALUES('Book', 'Harry Potter', 'JK Rowling', 'Harry yer a wizard', 'Fantasy', 1999, 'true', 150);

INSERT INTO loan(item_id, user_id) VALUES(1, 1);



