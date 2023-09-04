--liquibase formatted sql
--changeset pavel:insert-table;
insert into roles (role_id, role_name)
values
(uuid_generate_v4(),'ADMIN');
create extension if not exists "uuid-ossp";


