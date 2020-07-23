--date: 2020-07-12
--author: fedorov

create table comments
(
    id        bigint primary key auto_increment,
    date_time timestamp,
    book_id   bigint,
    text      varchar(250),
    foreign key (book_id) references books (id)
)
