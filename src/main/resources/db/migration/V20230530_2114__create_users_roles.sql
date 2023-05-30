create table users_roles (
                       roles bigint references roles(role_id),
                       users bigint references users(user_id)
);

insert into users_roles (roles, users) values
      (1, 1),
      (2, 2),
      (3, 3),
      (4, 4),
      (5, 5),
      (6, 1) ;