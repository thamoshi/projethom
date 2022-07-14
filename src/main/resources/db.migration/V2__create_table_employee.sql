create table employee(
    id UUID not null,
    person_id UUID not null,
    team_id UUID not null,
    role varchar(50) not null,
    created_at datetime not null,
    updated_at datetime not null,
    primary key(id),
    foreign key(team_id) references team(id)
);
