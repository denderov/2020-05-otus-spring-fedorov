insert into authors (id, full_name)
values (101, 'Test_author_1'),
       (102, 'Test_author_2'),
       (103, 'Test_author_3');

insert into genres (id, name)
values (201, 'Test_genre_1'),
       (202, 'Test_genre_2'),
       (203, 'Test_genre_3');

insert into books (id, title, author_id, genre_id)
values (1, 'Test_book_1', 101, 201),
       (2, 'Test_book_2', 102, 202)
