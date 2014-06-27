\connect giant;

create table product(
id SERIAL PRIMARY KEY,
name varchar(200)
);

create table pricing(
id SERIAL PRIMARY KEY,
amount double precision,
productId int
);