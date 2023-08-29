--create type u_Role as ENUM ('ADMIN', 'JOURNALIST', 'SUBSCRIBER');

create table users
(
    id         uuid        not null
        primary key,
    first_name varchar(64) not null ,
    last_name  varchar(64) not null,
    email      varchar(256) not null ,
    password   varchar(256) not null ,
    role       varchar(64)
);

create unique index users_email_uindex on users (email);
create extension if not exists "uuid-ossp";