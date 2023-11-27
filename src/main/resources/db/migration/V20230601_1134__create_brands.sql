create table brands
(
    brand_id bigserial PRIMARY KEY NOT NULL,
    name     varchar(128)          NOT NULL UNIQUE
);

insert into brands (name)
values ('Brand #1'),
       ('Brand #2'),
       ('Brand #3'),
       ('Brand #4');