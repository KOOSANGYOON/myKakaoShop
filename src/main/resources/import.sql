--INSERT INTO user(id, create_date, modified_date, deleted, email, mileage, name, passwd, user_id) VALUES (1, 201801010830, 201801010830, false, 'koo@naver.com', 0, '구상윤', '$2a$10$a/WCW.09vXNbMzRvRC5AVepdlpKG/1Y/VTqlFw8jbUk6/tsVcGsaW', 'koo');

INSERT INTO item(id, create_date, modified_date, contents, cost, main_image_path, name) values (1, 201801010830, 201801010830, 'sleep clothes for woman', 13900, '/images/productImages/product1_1.jpg', 'sleep clothes');

INSERT INTO image(id, name, path, item_id) values (1, 'product1_1.jpg', '/images/productImages/product1_1.jpg', 1);