SET client_encoding = 'UTF8';

create table users (
    user_id serial PRIMARY KEY,
	username VARCHAR ( 100 ) UNIQUE NOT NULL,
	password VARCHAR ( 100 ) NOT NULL
);

