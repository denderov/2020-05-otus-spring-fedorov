--liquibase formatted sql

--changeset fedorov:2020-07-12-001-authors
create table authors
(
    id        bigint primary key auto_increment,
    full_name varchar2
);