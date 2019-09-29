--SET AUTOCOMMIT = ON
CREATE DATABASE orders;

CREATE TABLE account(
   user_id serial PRIMARY KEY,
   username VARCHAR (50),
   password VARCHAR (50),
   email VARCHAR (355)
);


select * from account;

insert into account(user_id, username, "password", email)
    values(1, 'xbl', '123456', 'email');
