insert into authors (id, full_name)
values (101, 'Кей Хорстманн'),
       (102, 'Дориан Грей');

insert into genres (id, name)
values (201, 'Программирование'),
       (202, 'Художественная литература');

insert into books (id, title, author_id, genre_id)
values (1, 'Стринги', 101, 201)