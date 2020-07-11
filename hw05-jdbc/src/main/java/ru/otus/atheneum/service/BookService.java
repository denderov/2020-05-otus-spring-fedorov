package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public interface BookService {

  void prepareAll();

  Optional<Book> save(String title, Author author, Genre genre);

  BookService initBook();

  BookService setTitle(String bookTitle);

  BookService setAuthor(Author author);

  BookService setGenre(Genre genre);

  Optional<Book> createBook();

  List<Book> getPreparedBookList();

  Optional<Book> getBookFromPreparedListByPosition(int bookPosition);

  void updateBook(Book bookForUpdate);
}
