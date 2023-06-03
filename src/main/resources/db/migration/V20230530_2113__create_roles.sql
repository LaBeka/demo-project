create table roles (
role_id bigserial PRIMARY KEY NOT NULL,
role_name varchar(128) NOT NULL UNIQUE
);

insert into roles (role_name) values
('ADMIN'), ('CUSTOMER'), ('GUEST'), ('MANAGER'), ('USER')