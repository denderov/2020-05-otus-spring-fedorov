package ru.otus;

import java.util.List;
import lombok.experimental.UtilityClass;
import ru.otus.domain.Author;

@UtilityClass
public class TestHelper {

  public final long AUTHOR_ID_1 = 1L;
  public final long AUTHOR_ID_2 = 2L;
  public final String AUTHOR_FULL_NAME_1 = "Test_author_1";
  public final String AUTHOR_FULL_NAME_2 = "Test_author_2";
  public final Author AUTHOR_1 = new Author(AUTHOR_ID_1, AUTHOR_FULL_NAME_1);
  public final Author AUTHOR_2 = new Author(AUTHOR_ID_2, AUTHOR_FULL_NAME_2);

  public final List<Author> AUTHORS = List.of(AUTHOR_1, AUTHOR_2);
}
