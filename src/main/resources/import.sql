--INSERT INTO user(id, create_date, modified_date, deleted, email, mileage, name, passwd, user_id) VALUES (1, 201801010830, 201801010830, false, 'koo@naver.com', 0, '구상윤', '$2a$10$a/WCW.09vXNbMzRvRC5AVepdlpKG/1Y/VTqlFw8jbUk6/tsVcGsaW', 'koo');

INSERT INTO item(id, contents, cost, main_image_path, name) values (1, 'sleep clothes for woman', 59900, '/images/productImages/product1.jpg', 'sleep clothes');
INSERT INTO item(id, contents, cost, main_image_path, name) values (2, 'stylish brick with ryan', 49000, '/images/productImages/product2.jpg', 'castle brick');
INSERT INTO item(id, contents, cost, main_image_path, name) values (3, 'led light with ryan', 29000, '/images/productImages/product3.jpg', 'led light');
INSERT INTO item(id, contents, cost, main_image_path, name) values (4, 'ryan pattern sneakers', 65000, '/images/productImages/product4.jpg', 'pattern sneakers');
INSERT INTO item(id, contents, cost, main_image_path, name) values (5, 'big size ryan doll', 90000, '/images/productImages/product5.jpg', 'big size doll');
INSERT INTO item(id, contents, cost, main_image_path, name) values (6, 'gentle man ryan figure', 29000, '/images/productImages/product6.jpg', 'gentleman figure');
INSERT INTO item(id, contents, cost, main_image_path, name) values (7, 'hotel de ryan wine glasses', 24000, '/images/productImages/product7.jpg', 'wine glasses');
INSERT INTO item(id, contents, cost, main_image_path, name) values (8, 'cushion with new friend', 22000, '/images/productImages/product8.jpg', 'cushion');
INSERT INTO item(id, contents, cost, main_image_path, name) values (9, 'yellow umbrella with ryan', 22000, '/images/productImages/product9.jpg', 'umbrella');
INSERT INTO item(id, contents, cost, main_image_path, name) values (10, '5cm prodo figure', 9000, '/images/productImages/product10.jpg', 'prodo figure');

INSERT INTO image(id, name, path, item_id) values (1, 'product1.jpg', '/images/productImages/product1.jpg', 1);
INSERT INTO image(id, name, path, item_id) values (2, 'product2.jpg', '/images/productImages/product2.jpg', 2);
INSERT INTO image(id, name, path, item_id) values (3, 'product3.jpg', '/images/productImages/product3.jpg', 3);
INSERT INTO image(id, name, path, item_id) values (4, 'product4.jpg', '/images/productImages/product4.jpg', 4);
INSERT INTO image(id, name, path, item_id) values (5, 'product5.jpg', '/images/productImages/product5.jpg', 5);
INSERT INTO image(id, name, path, item_id) values (6, 'product6.jpg', '/images/productImages/product6.jpg', 6);
INSERT INTO image(id, name, path, item_id) values (7, 'product7.jpg', '/images/productImages/product7.jpg', 7);
INSERT INTO image(id, name, path, item_id) values (8, 'product8.jpg', '/images/productImages/product8.jpg', 8);
INSERT INTO image(id, name, path, item_id) values (9, 'product9.jpg', '/images/productImages/product9.jpg', 9);
INSERT INTO image(id, name, path, item_id) values (10, 'product10.jpg', '/images/productImages/product10.jpg', 10);