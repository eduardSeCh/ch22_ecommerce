INSERT INTO customer(first_name, last_name, email, password, is_active, avatar) VALUES ('Patricio', 'Estrella', 'estrella@gmail.com','$2a$10$tTnkyQO6v5DqFDwP79ioj.d0T8HjOhKRDvPc8qyG8xqnQgnLLSdK.', true, 'https://randomuser.me/api/portraits/men/78.jpg');
INSERT INTO customer(first_name, last_name, email, password, is_active, avatar) VALUES ('Benito', 'Bodoque', 'bodoque@gmail.com','$2a$10$tTnkyQO6v5DqFDwP79ioj.d0T8HjOhKRDvPc8qyG8xqnQgnLLSdK.', false,'https://randomuser.me/api/portraits/men/0.jpg');
INSERT INTO customer(first_name, last_name, email, password, is_active, avatar) VALUES ('Selena','Gómez', 'gomez@gmail.com','$2a$10$tTnkyQO6v5DqFDwP79ioj.d0T8HjOhKRDvPc8qyG8xqnQgnLLSdK.',true,'https://randomuser.me/api/portraits/women/66.jpg');
INSERT INTO customer(first_name, last_name, email, password, is_active, avatar) VALUES ('Edna', 'Krabappel', 'edna@gmail.com','$2a$10$tTnkyQO6v5DqFDwP79ioj.d0T8HjOhKRDvPc8qyG8xqnQgnLLSdK.',true, 'https://randomuser.me/api/portraits/women/88.jpg');
INSERT INTO customer(first_name, last_name, email, password, is_active, avatar) VALUES ('Cristiano', 'Ronaldo', 'siu@gmail.com','$2a$10$tTnkyQO6v5DqFDwP79ioj.d0T8HjOhKRDvPc8qyG8xqnQgnLLSdK.', false, 'https://randomuser.me/api/portraits/men/3.jpg');

INSERT INTO address(zip_Code, address, city, fk_id_customer) VALUES ('666', 'una Roca', 'Fondo de Bikini',1);
INSERT INTO address(zip_Code, address, city, fk_id_customer) VALUES ('666', 'una piña', 'A lado de la roca',1);
INSERT INTO address(zip_Code, address, city, fk_id_customer) VALUES ('543', 'Av Siempre viva', 'California',2);