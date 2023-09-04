create table roles
(
    role_id         uuid not null primary key,
    role_name varchar(64) not null
);

create extension if not exists "uuid-ossp";