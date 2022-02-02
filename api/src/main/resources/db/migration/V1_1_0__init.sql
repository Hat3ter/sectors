-- init table
create table sectors
(
    id          uuid not null
        constraint sectors_pkey
            primary key,
    agree_terms boolean,
    parent_id   uuid,
    sector_name varchar(100)
);

create table users
(
    id               uuid         not null
        constraint users_pkey
            primary key,
    name             varchar(255) not null,
    agree_with_terms boolean
);

create table agreed_sectors
(
    id uuid not null constraint agreed_sectors_pkey
        primary key,
    sector_id uuid not null,
    user_id   uuid
);