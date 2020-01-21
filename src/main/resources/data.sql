INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'John', 'Smith', 'john.smith@gmail.com', 'jsmith', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'Jane', 'Smith', 'jane.smith@gmail.com', 'janesmith', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'Hagr', 'Saad', 'hagr@gmail.com', 'hsaad', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Borrower', 'Johnathan', 'Smith', 'jonsmith@gmail.com', 'jonathan', '1234567');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Admin', 'Hagr', 'Saad', 'hagrsaad@gmail.com', 'hagr', '12345');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Librarian', 'Joe', 'Brown', 'helloworld@gmail.com', 'joe', '12345');
INSERT INTO cuser(user_type, first_name, last_name, email, username, pw) VALUES('Librarian', 'Jack', 'Brown', 'jack.brown@gmail.com', 'jack', '12345');


INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('Book', 'Harry Potter', 'JK Rowling', 'Harry yer a wizard', 'Fantasy', 1994, 'true', 150, 'https://vignette.wikia.nocookie.net/harrypotter/images/7/7b/Harry01english.jpg/revision/latest?cb=20150208225304');

INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('Book', 'Twilight', 'Stephanie Meyer', 'A vampire love story', 'Romance', 2010, 'true', 150, 'https://images-na.ssl-images-amazon.com/images/I/41K99%2BcInvL._SX326_BO1,204,203,200_.jpg');

INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('Book', 'Hunger Games', 'Collins', 'A face off between teens', 'Fantasy', 2014, 'true', 150, 'https://upload.wikimedia.org/wikipedia/en/thumb/d/dc/The_Hunger_Games.jpg/220px-The_Hunger_Games.jpg');
			
INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('Book', 'The Help', 'Kathryn Stockett', 'A southern maid story', 'Fiction', 1995, 'true', 150, 'https://images-na.ssl-images-amazon.com/images/I/91fvGdAACYL.jpg');
				
INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('Book', 'Water for Elephants', 'Sara Gruen', 'A circus love story', 'Romance', 2015, 'true', 150, 'https://upload.wikimedia.org/wikipedia/en/e/e7/Water_for_elephants.jpg');


INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('AudioBook', 'Harry Potter', 'JK Rowling', 'Harry yer a wizard', 'Fantasy', 1994, 'true', 150, 'https://vignette.wikia.nocookie.net/harrypotter/images/7/7b/Harry01english.jpg/revision/latest?cb=20150208225304');

INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('AudioBook', 'Twilight', 'Stephanie Meyer', 'A vampire love story', 'Romance', 2010, 'true', 150, 'https://images-na.ssl-images-amazon.com/images/I/41K99%2BcInvL._SX326_BO1,204,203,200_.jpg');

INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('AudioBook', 'Hunger Games', 'Collins', 'A face off between teens', 'Fantasy', 2014, 'true', 150, 'https://upload.wikimedia.org/wikipedia/en/thumb/d/dc/The_Hunger_Games.jpg/220px-The_Hunger_Games.jpg');
			
INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('AudioBook', 'The Help', 'Kathryn Stockett', 'A southern maid story', 'Fiction', 1995, 'true', 150, 'https://images-na.ssl-images-amazon.com/images/I/91fvGdAACYL.jpg');
				
INSERT INTO libitem(item_type, title, creator, description, genre, publication_year, available, file_size, img_src) VALUES('AudioBook', 'Water for Elephants', 'Sara Gruen', 'A circus love story', 'Romance', 2015, 'true', 150, 'https://upload.wikimedia.org/wikipedia/en/e/e7/Water_for_elephants.jpg');		



INSERT INTO loan(item_id, user_id) VALUES(1, 1);



