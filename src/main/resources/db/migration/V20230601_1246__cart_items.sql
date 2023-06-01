create table cart_items (
item_id bigserial PRIMARY KEY NOT NULL,
cart_id bigint REFERENCES carts(cart_id) ON DELETE CASCADE,
product_id bigint REFERENCES products(product_id) ON DELETE CASCADE,
qty int
);