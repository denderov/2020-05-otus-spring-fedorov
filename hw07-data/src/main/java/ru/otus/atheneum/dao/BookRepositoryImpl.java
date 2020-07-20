package ru.otus.atheneum.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.domain.Book;

//@Repository
//бином делать необязательно, как выясняется
public class BookRepositoryImpl implements BookRepositoryCustom {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  CommentRepository commentRepository;

  @Override
  public void deleteWithComments(Book book) {
    commentRepository.deleteAllByBook(book);
    bookRepository.delete(book);
  }
}
