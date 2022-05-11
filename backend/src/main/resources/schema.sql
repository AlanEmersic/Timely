create table if not exists project
(
    id  serial          primary key,
    name varchar(255) not null,
    start_time timestamp not null,
    end_time timestamp not null,
    duration interval not null
);