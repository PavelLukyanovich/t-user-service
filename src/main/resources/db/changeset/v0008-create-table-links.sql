create table links
(
    link_id uuid not null primary key,
    link varchar(255)
);

create extension if not exists "uuid-ossp";