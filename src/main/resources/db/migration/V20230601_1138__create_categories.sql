create table categories (
                        category_id bigserial PRIMARY KEY NOT NULL,
                        name varchar(128) NOT NULL UNIQUE
);

insert into categories (name) values
('Accessories for kitchen'),
('Accessories for living room'),
('Accessories for bathroom'),
('Home plants');