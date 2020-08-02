package ru.otus.atheneum.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import ru.otus.domain.Book;

@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
public class BookRepositoryImpl implements BookRepositoryCustom {

  final private BookRepository bookRepository;
  final private CommentRepository commentRepository;

  @Override
  public void deleteWithComments(Book book) {
    commentRepository.deleteAllByBook(book);
    bookRepository.delete(book);
  }
}
