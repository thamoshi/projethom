create table system(
    id UUID not null,
    name varchar(50) not null,
    owner_id UUID,
    documentation varchar(200) not null,
    url varchar(100) not null,
    created_at datetime not null,
    updated_at datetime not null,
    primary key(id),
    foreign key(owner_id) references team(id)
);
