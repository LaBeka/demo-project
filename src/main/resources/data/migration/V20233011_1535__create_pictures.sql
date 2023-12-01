create table picture_entities
(
    picture_id    bigserial PRIMARY KEY NOT NULL,
    picture_name          varchar(128)          NOT NULL,
    product_id    bigserial NOT NULL
);

insert into picture_entities (picture_name, product_id)
values ('bowlJossMain.jpg', 1),
       ('cuttingBar.jpg', 2),
       ('flowers.jpg', 3),
       ('ivaGlassDecorativeBowl.jpg', 4),
       ('silkBambooTree.jpg', 5),
       ('smallKnife.jpg', 6),
       ('wairFairbasics.jpg', 7);