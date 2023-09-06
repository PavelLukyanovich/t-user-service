alter table users drop column role_id;
CREATE TYPE Role AS (f1 uuid, f2 text);
alter table users add column role Role;


