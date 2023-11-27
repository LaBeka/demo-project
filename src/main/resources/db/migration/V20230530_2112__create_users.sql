create table users
(
    user_id      bigserial PRIMARY KEY NOT NULL,
    full_name    varchar(328)          NOT NULL,
    account_name varchar(128)          NOT NULL,
    email        varchar(128)          NOT NULL UNIQUE,
    password     varchar(128)          NOT NULL,
    enabled      BOOLEAN               NOT NULL
);

insert into users (full_name, account_name, email, password, enabled)
values ('Alina', 'Alieva', 'aline@gmail.com',
        '$2a$10$3UbaP8cjNLGho.M1wVuZAOw4QnlqEQGuiewRyc5aEOYn88A9qPyQy', 'true'),
       ('Aida', 'Aleva', 'aida@gmail.com',
        '$2a$10$3UbaP8cjNLGho.M1wVuZAOw4QnlqEQGuiewRyc5aEOYn88A9qPyQy', 'true'),
       ('Rita', 'Kalieva', 'rita@gmail.com',
        '$2a$10$3UbaP8cjNLGho.M1wVuZAOw4QnlqEQGuiewRyc5aEOYn88A9qPyQy', 'true'),
       ('Nika', 'Kaliev', 'nika@gmail.com',
        '$2a$10$3UbaP8cjNLGho.M1wVuZAOw4QnlqEQGuiewRyc5aEOYn88A9qPyQy', 'true'),
       ('Becka', 'Kaleva', 'becka@gmail.com',
        '$2a$10$3UbaP8cjNLGho.M1wVuZAOw4QnlqEQGuiewRyc5aEOYn88A9qPyQy', 'true'),
       ('Mika', 'Kalev', 'mika@gmail.com',
        '$2a$10$3UbaP8cjNLGho.M1wVuZAOw4QnlqEQGuiewRyc5aEOYn88A9qPyQy', 'true'),
       ('Mancy', 'Mancy', 'mancy@mail.com',
        '$2a$10$3UbaP8cjNLGho.M1wVuZAOw4QnlqEQGuiewRyc5aEOYn88A9qPyQy', 'true');
