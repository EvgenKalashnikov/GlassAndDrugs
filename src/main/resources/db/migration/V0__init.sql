SET client_encoding = 'UTF8';

create table users (
    id serial PRIMARY KEY,
	name VARCHAR ( 100 ) UNIQUE NOT NULL,
	password VARCHAR ( 100 ) NOT NULL
);

