create table picture_entities
(
    picture_id    bigserial PRIMARY KEY NOT NULL,
    picture_name          varchar(128)          NOT NULL,
    product_id    bigserial NOT NULL
);
