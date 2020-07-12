package ru.otus;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import lombok.experimental.UtilityClass;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

@UtilityClass
public class TestHelper {

  public final long AUTHOR_ID_1 = 101L;
  public final long AUTHOR_ID_2 = 102L;
  public final long AUTHOR_ID_3 = 103L;
  public final String AUTHOR_FULL_NAME_1 = "Test_author_1";
  public final String AUTHOR_FULL_NAME_2 = "Test_author_2";
  public final String AUTHOR_FULL_NAME_3 = "Test_author_3";
  public final Author AUTHOR_1 = new Author(AUTHOR_ID_1, AUTHOR_FULL_NAME_1);
  public final Author AUTHOR_2 = new Author(AUTHOR_ID_2, AUTHOR_FULL_NAME_2);
  public final Author AUTHOR_3 = new Author(AUTHOR_ID_3, AUTHOR_FULL_NAME_3);
  public final List<Author> AUTHORS = List.of(AUTHOR_1, AUTHOR_2, AUTHOR_3);

  public final long GENRE_ID_1 = 201L;
  public final long GENRE_ID_2 = 202L;
  public final long GENRE_ID_3 = 203L;
  public final String GENRE_NAME_1 = "Test_genre_1";
  public final String GENRE_NAME_2 = "Test_genre_2";
  public final String GENRE_NAME_3 = "Test_genre_3";
  public final Genre GENRE_1 = new Genre(GENRE_ID_1, GENRE_NAME_1);
  public final Genre GENRE_2 = new Genre(GENRE_ID_2, GENRE_NAME_2);
  public final Genre GENRE_3 = new Genre(GENRE_ID_3, GENRE_NAME_3);
  public final List<Genre> GENRES = List.of(GENRE_1, GENRE_2, GENRE_3);


  public final long COMMENT_ID_1 = 301L;
  public final long COMMENT_ID_2 = 302L;
  public final LocalDateTime COMMENT_DATE_TIME_1 = LocalDateTime
      .of(2020, Month.JANUARY, 1, 0, 0, 0);
  public final LocalDateTime COMMENT_DATE_TIME_2 = LocalDateTime
      .of(2020, Month.JANUARY, 13, 0, 0, 0);
  public final String COMMENT_TEXT_1 = "Test_comment_1";
  public final String COMMENT_TEXT_2 = "Test_comment_2";
  public final Comment COMMENT_1 = new Comment(COMMENT_DATE_TIME_1, COMMENT_TEXT_1, COMMENT_ID_1);
  public final Comment COMMENT_2 = new Comment(COMMENT_DATE_TIME_2, COMMENT_TEXT_2, COMMENT_ID_2);
  public final List<Comment> COMMENTS_1 = List.of(COMMENT_1);
  public final List<Comment> COMMENTS_2 = List.of(COMMENT_2);
  public final List<Comment> COMMENTS = List.of(COMMENT_1, COMMENT_2);

  public final long BOOK_ID_1 = 1L;
  public final long BOOK_ID_2 = 2L;
  public final String BOOK_TITLE_1 = "Test_book_1";
  public final String BOOK_TITLE_2 = "Test_book_2";
  public final String BOOK_TITLE_3 = "Test_book_3";
  public final Book BOOK_1 = new Book(BOOK_ID_1, BOOK_TITLE_1, AUTHOR_1, GENRE_1, COMMENTS_1);
  public final Book BOOK_2 = new Book(BOOK_ID_2, BOOK_TITLE_2, AUTHOR_2, GENRE_2, COMMENTS_2);
  public final List<Book> BOOKS = List.of(BOOK_1, BOOK_2);
}
