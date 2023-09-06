alter table verificationtoken
    drop own_user;
alter table verificationtoken
add user_id uuid;


