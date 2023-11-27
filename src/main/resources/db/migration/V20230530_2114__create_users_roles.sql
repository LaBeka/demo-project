create table users_roles
(
    roles bigint references roles (role_id) ON DELETE CASCADE,
    users bigint references users (user_id) ON DELETE CASCADE
);

insert into users_roles (roles, users)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (1, 6);