--liquibase formatted sql
--changeset pavel:insert-table;
create extension if not exists "uuid-ossp";
insert into users (id, first_name, last_name, email, password, role)
values
(uuid_generate_v4(), 'Ivan', 'Ivanov', 'ii@gmail.com', '123456', 'ADMIN');


