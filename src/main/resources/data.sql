insert into categories (name_category)
VALUES ('Pasta'),
('Drink'),
('Salad'),
('Pizza'),
('Soup'),
('Dessert');



insert into dishes (dish_name,dish_comment,photo_path,dish_price,category_id)
VALUES ('Carbonara', 'maslo, makaronu al dente', '/storage/Carbonara.jpg', 2000,(select category_id from categories where name_category = 'Pasta')),
 ('Water', 'water', '/storage/Water.jpg', 20,(select category_id from categories where name_category = 'Drink')),
 ('Juice', 'lemon, orange, watermelon', '/storage/Juice.jpg', 20,(select category_id from categories where name_category = 'Drink')),
 ('Capreze', 'tomato, Mazarella, oil', '/storage/Capreze.jpeg', 2400,(select category_id from categories where name_category = 'Salad')),
       ('Caesar', 'prosciutto crudo, mozzarella cheese, salad mix, tomatoes, croutons, sun-dried tomatoes, carrots, blue onions', '/storage/Caesar.jpg', 2400,(select category_id from categories where name_category = 'Salad')),
       ('Sicily', 'salmon, salad mix, orange, cherry tomatoes, sesame, balsamic sauce', '/storage/Sicily.jpg', 2400,(select category_id from categories where name_category = 'Salad')),
       ('Branded salad from the chef', 'prosciutto crudo, mozzarella cheese, salad mix, tomatoes, croutons, sun-dried tomatoes, carrots, blue onions', '/storage/Branded salad from the chef.jpg', 2400,(select category_id from categories where name_category = 'Salad')),

       ('Margarita', 'testo, tomato, Mazarella, oil', '/storage/Margarita.jpg', 3166,(select category_id from categories where name_category = 'Pizza')),
 ('Borsh', 'kak y mami', '/storage/donald.jpg', 55000,(select category_id from categories where name_category = 'Soup')),
('Margarita', 'Pilate tomato sauce, mozzarella cheese, basil', '/storage/Margarita.jpg', 3166,(select category_id from categories where name_category = 'Pizza')),
('Alpina', 'cream sauce, mozzarella cheese, vitello tonnato, cherry tomatoes, arugula, mushrooms, olives, olives', '/storage/Alpina.jpg', 3166,(select category_id from categories where name_category = 'Pizza')),
('Toskana', 'Pilate tomato sauce, mozzarella cheese, salami, tomatoes, olives, olives, basil', '/storage/Toscana.jpg', 3166,(select category_id from categories where name_category = 'Pizza')),
('Panna cotta', 'jam, yogurt', '/storage/Panna cotta.jpg', 2000,(select category_id from categories where name_category = 'Dessert')),
 ('Cheesecake', 'cheese,eggs', '/storage/Cheesecake.jpg', 2000,(select category_id from categories where name_category = 'Dessert')),
 ('Bolognese', 'maslo,makaronu al dente', '/storage/Bolognese.jpg', 2400,(select category_id from categories where name_category = 'Pasta')),
 ('Sea-tasty', 'maslo,makaronu al dente', '/storage/Sea-tasty.jpg', 2070,(select category_id from categories where name_category = 'Pasta'));

insert into order_user (username)
VALUES ('testuser');

insert into order_list (user_id, created_date)
VALUES ((select user_id from order_user where username = 'testuser'), CURRENT_DATE),
       ((select user_id from order_user where username = 'testuser'), CURRENT_DATE);

insert into order_item (dish_id, order_list_id)
VALUES ((select dish_id from dishes where dish_name = 'Carbonara'),
       (select min(order_list_id) from order_list where user_id in (select user_id from order_user where username = 'testuser')));