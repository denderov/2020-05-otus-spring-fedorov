package ru.otus.homework.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import ru.otus.homework.domain.QuizItem;

public interface QuizDao {

  List<QuizItem> processQuizItemsFromDefaultCsv();

  List<QuizItem> getQuizItemsFromReader(Reader reader) throws IOException;
}
