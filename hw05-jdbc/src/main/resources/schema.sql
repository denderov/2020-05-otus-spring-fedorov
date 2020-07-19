create table authors
(
    id        bigint primary key auto_increment,
    full_name varchar2
);

create table genres
(
    id   bigint primary key auto_increment,
    name varchar2
);

create table books
(
    id        bigint primary key auto_increment,
    title     varchar2,
    author_id bigint,
    genre_id  bigint,
    foreign key (author_id) references authors (id),
    foreign key (genre_id) references genres (id)
);