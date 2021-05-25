package ru.otus.atheneum.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

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
