--liquibase formatted sql

--changeset fedorov:2020-07-12--001-books
insert into books (id, title, author_id, genre_id)
values (1, 'Стринги', 101, 201)