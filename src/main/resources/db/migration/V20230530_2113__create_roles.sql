create table roles (
                       role_id bigserial PRIMARY KEY NOT NULL ON DELETE CASCADE,
                       role_name varchar(128) NOT NULL ON DELETE CASCADE
);

insert into roles (role_name) values
      ('ADMIN'), ('CUSTOMER'), ('GUEST'), ('MANAGER'), ('USER')