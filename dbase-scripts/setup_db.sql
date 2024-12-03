CREATE DATABASE acsd;

create table tr_crs  (
    id bigint NOT NULL,
    tr_date varchar(20) not null,
    days integer,
    ttl_seats integer,
    avail integer,
    type char(10),
    curr_price integer,
    full_price integer);


