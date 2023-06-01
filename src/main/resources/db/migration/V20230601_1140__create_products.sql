create table products (
product_id bigserial PRIMARY KEY NOT NULL,
name varchar(128) NOT NULL,
image varchar(128),
description text,
initial_price float NOT NULL,
storage_qty integer NOT NULL,
discount integer,
new_product boolean NOT NULL,
brand_id bigint REFERENCES brands(brand_id) ON DELETE CASCADE ,
category_id bigint REFERENCES categories(category_id) ON DELETE CASCADE
);

insert into products (name, image, description, initial_price, storage_qty, discount, new_product, brand_id, category_id) values
('Cutting bar', 'cuttingBar.jpg', 'The Math.ceil() static method always rounds up and returns the smaller integer greater than or equal to a given number.', '110.90', '20', '0', 'true', 1, 1),
('Small knife', 'smallKnife.jpg', 'ceil() function in JavaScript uses the upward rounding method, which accepts a single input, the number to be rounded to the nearest integer.', '54.95', '20', '0', 'true', 2, 1),
('Iva Glass Decorative Bowl', 'ivaGlassDecorativeBowl.jpg', 'round( ) in that it always rounds up, rather than rounding up or down to the closest integer. Also note that Math.ceil( ) does not round negative numbers to ...', '33.93', '20', '0', 'true', 3, 2),
('Bowl by Joss & Main', 'bowlJossMain.jpg', 'The ceil() method rounds a decimal number up to the next largest integer and returns it. That is, 4.3 will be rounded to 5 (next largest integer). Example. let ...', '116.85', '20', '0', 'true', 4, 2),
('Wair Fair basics', 'wairFairbasics.jpg', 'The JavaScript math ceil() method increases the given number up to the closest integer value and returns it. If the given number is already an integer, ...', '79.50', '20', '0', 'true', 1, 3),
('Silk bamboo tree', 'silkBambooTree.jpg', 'The Math.ceil function in JavaScript is used to round up a number that is passed into it to its nearest integer. What do I mean by rounding up?', '49.10', '20', '0', 'true', 2, 4);