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