package ru.otus.atheneum.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.domain.Book;

public class BookRepositoryImpl implements BookRepositoryCustom {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Override
  public void deleteWithComments(Book book) {
    commentRepository.deleteAllByBook(book);
    bookRepository.delete(book);
  }
}
