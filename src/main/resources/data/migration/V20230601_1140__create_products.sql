create table product_entities
(
    product_id    bigserial PRIMARY KEY NOT NULL,
    name          varchar(128)          NOT NULL,
    description   text,
    initial_price float                 NOT NULL,
    storage_qty   integer               NOT NULL,
    discount      integer,
    current_price float,
    new_product   boolean               NOT NULL,
    brand_name      varchar(128)          NOT NULL,
    category_name   varchar(128)          NOT NULL
);

insert into product_entities (name, description, initial_price, storage_qty, discount, current_price,
                              new_product, brand_name, category_name)
values ('Cutting bar',
        'The Math.ceil() static method always rounds up and returns the smaller integer greater than or equal to a given number.',
        '110.90', '20', '0', 110.90, 'true', 'BRAND_1', 'CATEGORY_1'),
       ('Small knife',
        'ceil() function in JavaScript uses the upward rounding method, which accepts a single input, the number to be rounded to the nearest integer.',
        '54.95', '20', '0', 54.95, 'true', 'BRAND_2', 'CATEGORY_2'),
       ('Iva Glass Decorative Bowl',
        'round( ) in that it always rounds up, rather than rounding up or down to the closest integer. Also note that Math.ceil( ) does not round negative numbers to ...',
        '33.93', '20', '5', 32.23, 'true', 'BRAND_3', 'CATEGORY_3'),
       ('Bowl by Joss & Main',
        'The ceil() method rounds a decimal number up to the next largest integer and returns it. That is, 4.3 will be rounded to 5 (next largest integer). Example. let ...',
        '116.85', '20', '30', 81.79, 'true', 'BRAND_4', 'CATEGORY_4'),
       ('Wair Fair basics',
        'The JavaScript math ceil() method increases the given number up to the closest integer value and returns it. If the given number is already an integer, ...',
        '79.50', '20', '60', 31.80, 'true', 'BRAND_4', 'CATEGORY_4'),
       ('Silk bamboo tree',
        'The Math.ceil function in JavaScript is used to round up a number that is passed into it to its nearest integer. What do I mean by rounding up?',
        '49.10', '20', '80', 9.82, 'true', 'BRAND_3', 'CATEGORY_3'),
       ('Silk bamboo PLANT TREE',
        'The Math.ceil function in JavaScript is used to round up a number that is passed into it to its nearest integer. What do I mean by rounding up?',
        '49.10', '20', '80', 9.82, 'true', 'BRAND_3', 'CATEGORY_3');