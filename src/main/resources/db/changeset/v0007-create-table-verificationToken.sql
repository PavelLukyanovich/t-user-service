create table verificationToken
(
    id uuid not null primary key,
    token varchar(255),
    own_user users,
    expire_date date
);

create extension if not exists "uuid-ossp";