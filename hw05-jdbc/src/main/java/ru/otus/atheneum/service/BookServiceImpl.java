package ru.otus.atheneum.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.BookDao;
import ru.otus.domain.Book;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDao bookDao;

  @Override
  public List<Book> getAll() {
    return bookDao.getAll();
  }

}
