--liquibase formatted sql

--changeset fedorov:2020-07-12-001-genres
create table genres
(
    id   bigint primary key auto_increment,
    name varchar2
);