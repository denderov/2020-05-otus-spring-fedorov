package ru.otus.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.atheneum.TestHelper;
import ru.otus.domain.Author;

@ChangeLog(order = "001")
public class InitMongoTestChangelog {

  private Author author;

  @ChangeSet(order = "000", id = "dropDB", author = "fedorov", runAlways = true)
  public void dropDB(MongoDatabase database) {
    database.drop();
  }

  @ChangeSet(order = "001", id = "initAuthors", author = "fedorov", runAlways = true)
  public void initAuthors(MongockTemplate template) {
    author = template.save(TestHelper.AUTHOR_1);
  }
}
