create table carts
(
    cart_id bigserial PRIMARY KEY NOT NULL,
    user_id bigint REFERENCES users (user_id) ON DELETE CASCADE
);