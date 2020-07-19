--liquibase formatted sql

--changeset fedorov:2020-07-12--001-authors
insert into authors (id, full_name)
values (101, 'Кей Хорстманн'),
       (102, 'Дориан Грей');