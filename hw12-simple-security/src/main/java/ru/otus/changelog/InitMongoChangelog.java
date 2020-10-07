package ru.otus.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDateTime;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.domain.Users;

@ChangeLog(order = "001")
public class InitMongoChangelog {

  private Author author1;
  private Author author2;
  private Genre genre1;
  private Genre genre2;
  private Book book;

  @ChangeSet(order = "000", id = "dropDB", author = "fedorov", runAlways = true)
  public void dropDB(MongoDatabase database) {
    database.drop();
  }

  @ChangeSet(order = "0001", id = "initUsers", author = "fedorov", runAlways = true)
  public void initUsers(MongockTemplate template) {
    template.save(new Users("admin", "123", "ADMIN"));
    template.save(new Users("user", "1", "USER"));
  }

  @ChangeSet(order = "001", id = "initAuthors", author = "fedorov", runAlways = true)
  public void initAuthors(MongockTemplate template) {
    author1 = template.save(new Author("Кей Хорстманн"));
    author2 = template.save(new Author("Дориан Грей"));
  }

  @ChangeSet(order = "002", id = "initGenres", author = "fedorov", runAlways = true)
  public void initGenres(MongockTemplate template) {
    genre1 = template.save(new Genre("Программирование"));
    genre2 = template.save(new Genre("Художественная литература"));
  }

  @ChangeSet(order = "003", id = "initBooks", author = "fedorov", runAlways = true)
  public void initBooks(MongockTemplate template) {
    book = template.save(new Book("Стринги", author1, genre1));
  }

  @ChangeSet(order = "004", id = "initComments", author = "fedorov", runAlways = true)
  public void initComments(MongockTemplate template) {
    template.save(new Comment(LocalDateTime.of(2020, 1, 1, 0, 0), book, "Первый комментарий"));
    template.save(new Comment(LocalDateTime.of(2020, 1, 13, 0, 0), book, "Второй комментарий"));
  }

}
