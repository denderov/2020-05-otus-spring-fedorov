package ru.otus.common;

import lombok.experimental.UtilityClass;
import ru.otus.mongo.domain.MongoAuthor;
import ru.otus.mongo.domain.MongoBook;
import ru.otus.mongo.domain.MongoGenre;

import java.util.List;

@UtilityClass
public class TestHelper {

  public final String AUTHOR_ID_1 = "101L";
  public final String AUTHOR_ID_2 = "102L";
  public final String AUTHOR_ID_3 = "103L";
  public final String AUTHOR_FULL_NAME_1 = "Test_author_1";
  public final String AUTHOR_FULL_NAME_2 = "Test_author_2";
  public final String AUTHOR_FULL_NAME_3 = "Test_author_3";
  public final MongoAuthor AUTHOR_1 = new MongoAuthor(AUTHOR_ID_1, AUTHOR_FULL_NAME_1);
  public final MongoAuthor AUTHOR_2 = new MongoAuthor(AUTHOR_ID_2, AUTHOR_FULL_NAME_2);
  public final MongoAuthor AUTHOR_3 = new MongoAuthor(AUTHOR_ID_3, AUTHOR_FULL_NAME_3);
  public final List<MongoAuthor> AUTHORS = List.of(AUTHOR_1, AUTHOR_2, AUTHOR_3);

  public final String GENRE_ID_1 = "201L";
  public final String GENRE_ID_2 = "202L";
  public final String GENRE_ID_3 = "203L";
  public final String GENRE_NAME_1 = "Test_genre_1";
  public final String GENRE_NAME_2 = "Test_genre_2";
  public final String GENRE_NAME_3 = "Test_genre_3";
  public final MongoGenre GENRE_1 = new MongoGenre(GENRE_ID_1, GENRE_NAME_1);
  public final MongoGenre GENRE_2 = new MongoGenre(GENRE_ID_2, GENRE_NAME_2);
  public final MongoGenre GENRE_3 = new MongoGenre(GENRE_ID_3, GENRE_NAME_3);
  public final List<MongoGenre> GENRES = List.of(GENRE_1, GENRE_2, GENRE_3);


  public final String BOOK_ID_1 = "1L";
  public final String BOOK_ID_2 = "2L";
  public final String BOOK_TITLE_1 = "Test_book_1";
  public final String BOOK_TITLE_2 = "Test_book_2";
  public final String BOOK_TITLE_3 = "Test_book_3";
  public final MongoBook BOOK_1 = new MongoBook(BOOK_ID_1, BOOK_TITLE_1, AUTHOR_1, GENRE_1);
  public final MongoBook BOOK_2 = new MongoBook(BOOK_ID_2, BOOK_TITLE_2, AUTHOR_2, GENRE_2);
  public final List<MongoBook> BOOKS = List.of(BOOK_1, BOOK_2);
}
