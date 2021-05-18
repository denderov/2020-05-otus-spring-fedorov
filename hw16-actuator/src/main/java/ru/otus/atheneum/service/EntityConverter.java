package ru.otus.atheneum.service;

import ru.otus.atheneum.dto.AuthorRow;
import ru.otus.atheneum.dto.BookRow;
import ru.otus.atheneum.dto.GenreRow;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public interface EntityConverter {

  BookRow convertBookEntityToDto(Book book);

  Book convertBookDtoToEntity(BookRow bookRow);

  AuthorRow convertAuthorEntityToDto(Author author);

  GenreRow convertGenreEntityToDto(Genre genre);
}
