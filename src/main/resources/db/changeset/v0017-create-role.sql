create type roles as enum ('ADMIN','SUBSCRIBER','JOURNALIST');

create table if not exists user_role
(
    role_name role not null primary key
);