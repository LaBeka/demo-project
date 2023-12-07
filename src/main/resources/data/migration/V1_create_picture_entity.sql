create table picture_entity
(
    picture_id       bigserial PRIMARY KEY NOT NULL,
    product_id       bigserial             NOT NULL,
    pic_content_type varchar(220),
    picture          bytea
);
