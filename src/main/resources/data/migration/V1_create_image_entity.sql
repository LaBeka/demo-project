create table image_entity
(
    image_id   bigserial PRIMARY KEY NOT NULL,
    product_id bigserial             NOT NULL,
    image_name varchar(220)
);
