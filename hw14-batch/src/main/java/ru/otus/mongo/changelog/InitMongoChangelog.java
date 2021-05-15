package ru.otus.mongo.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.mongo.domain.MongoAuthor;
import ru.otus.mongo.domain.MongoBook;
import ru.otus.mongo.domain.MongoGenre;

@ChangeLog(order = "001")
public class InitMongoChangelog {

  private MongoAuthor mongoAuthor1;
  private MongoAuthor mongoAuthor2;
  private MongoGenre mongoGenre1;
  private MongoGenre mongoGenre2;
  private MongoBook mongoBook;

  @ChangeSet(order = "000", id = "dropDB", author = "fedorov", runAlways = true)
  public void dropDB(MongoDatabase database) {
    database.drop();
  }

  @ChangeSet(order = "001", id = "initAuthors", author = "fedorov", runAlways = true)
  public void initAuthors(MongockTemplate template) {
    mongoAuthor1 = template.save(new MongoAuthor("Кей Хорстманн"));
    mongoAuthor2 = template.save(new MongoAuthor("Дориан Грей"));
  }

  @ChangeSet(order = "002", id = "initGenres", author = "fedorov", runAlways = true)
  public void initGenres(MongockTemplate template) {
    mongoGenre1 = template.save(new MongoGenre("Программирование"));
    mongoGenre2 = template.save(new MongoGenre("Художественная литература"));
  }

  @ChangeSet(order = "003", id = "initBooks", author = "fedorov", runAlways = true)
  public void initBooks(MongockTemplate template) {
    mongoBook = template.save(new MongoBook("Стринги", mongoAuthor1, mongoGenre1));
  }

}
