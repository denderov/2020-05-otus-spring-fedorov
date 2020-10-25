package ru.otus.testchangelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.atheneum.TestHelper;
import ru.otus.domain.Users;

@ChangeLog(order = "001")
public class InitMongoTestChangelog {

  @ChangeSet(order = "000", id = "dropDB", author = "fedorov", runAlways = true)
  public void dropDB(MongoDatabase database) {
    database.drop();
  }

  @ChangeSet(order = "0001", id = "initUsers", author = "fedorov", runAlways = true)
  public void initUsers(MongockTemplate template) {
    template.save(new Users("admin", "admin", "ADMIN"));
    template.save(new Users("user", "user", "USER"));
  }

  @ChangeSet(order = "001", id = "initAuthors", author = "fedorov", runAlways = true)
  public void initAuthors(MongockTemplate template) {
    template.save(TestHelper.AUTHOR_1);
    template.save(TestHelper.AUTHOR_2);
    template.save(TestHelper.AUTHOR_3);
  }

  @ChangeSet(order = "002", id = "initGenres", author = "fedorov", runAlways = true)
  public void initGenres(MongockTemplate template) {
    template.save(TestHelper.GENRE_1);
    template.save(TestHelper.GENRE_2);
    template.save(TestHelper.GENRE_3);
  }

  @ChangeSet(order = "003", id = "initBooks", author = "fedorov", runAlways = true)
  public void initBooks(MongockTemplate template) {
    template.save(TestHelper.BOOK_1);
    template.save(TestHelper.BOOK_2);
  }

  @ChangeSet(order = "004", id = "initComments", author = "fedorov", runAlways = true)
  public void initComments(MongockTemplate template) {
    template.save(TestHelper.COMMENT_1);
    template.save(TestHelper.COMMENT_2);
  }
}
