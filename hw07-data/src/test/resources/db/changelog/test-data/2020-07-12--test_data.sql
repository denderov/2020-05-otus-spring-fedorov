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
       (2, 'Test_book_2', 102, 202);

insert into comments (id, date_time, book_id, text)
values (301, '2020-01-01 00:00:00', 1, 'Test_comment_1'),
       (302, '2020-01-13 00:00:00', 2, 'Test_comment_2');
