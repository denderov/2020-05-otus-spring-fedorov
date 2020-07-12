--liquibase formatted sql

--changeset fedorov:2020-07-12-001-books
create table books
(
    id        bigint primary key auto_increment,
    title     varchar2,
    author_id bigint,
    genre_id  bigint,
    foreign key (author_id) references authors (id),
    foreign key (genre_id) references genres (id)
);