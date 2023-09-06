create type userRole as enum ('ADMIN', 'JOURNALIST', 'SUBSCRIBER');
create table user_role
(
    name userRole primary key not null
);