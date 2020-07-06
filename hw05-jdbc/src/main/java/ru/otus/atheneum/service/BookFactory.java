package ru.otus.atheneum.service;

import java.util.Optional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public interface BookFactory {

  String getTitle();

  BookFactory setTitle(String title);

  Author getAuthor();

  BookFactory setAuthor(Author author);

  Genre getGenre();

  BookFactory setGenre(Genre genre);

  Optional<Book> createBook();
}
