package ru.otus.atheneum.dao;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Override
  public void deleteWhenFree(Author author) {
    List<Book> books = bookRepository.findByAuthor(author);
    if (books.size() == 0) {
      authorRepository.delete(author);
    } else {
      throw new AuthorRepositoryException("Нельзя удалить автора у которого есть книги!");
    }
  }
}
