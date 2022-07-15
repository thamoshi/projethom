create table team(
    id UUID not null,
    name varchar(50) not null,
    description varchar(200),
    tribe varchar(50) not null,
    created_at date not null,
    updated_at date not null,
    primary key(id)
);
