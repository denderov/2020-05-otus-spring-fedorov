--liquibase formatted sql

--changeset fedorov:2020-07-12--001-genres
insert into genres (id, name)
values (201, 'Программирование'),
       (202, 'Художественная литература');