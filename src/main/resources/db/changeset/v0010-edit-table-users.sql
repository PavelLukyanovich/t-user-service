alter table users
    drop column role;
alter table users
add role_id uuid;


