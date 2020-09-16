package ru.otus.atheneum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dto.AuthorRow;
import ru.otus.atheneum.dto.BookRow;
import ru.otus.atheneum.dto.GenreRow;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@Slf4j
@RequiredArgsConstructor
public class EntityConverterImpl implements EntityConverter {

  private final AuthorService authorService;
  private final GenreService genreService;
  private final ModelMapper modelMapper;

  @Override
  public BookRow convertBookEntityToDto(Book book) {
    log.info(book.toString());
    modelMapper.typeMap(Book.class, BookRow.class).addMappings(
        mapper -> {
          mapper.map(src -> src.getAuthor().getFullName(), BookRow::setAuthor);
          mapper.map(src -> src.getGenre().getName(), BookRow::setGenre);
          mapper.map(src -> src.getAuthor().getId(), BookRow::setAuthorId);
          mapper.map(src -> src.getGenre().getId(), BookRow::setGenreId);
        }
    );
    return modelMapper.map(book, BookRow.class);
  }

  @Override
  public Book convertBookDtoToEntity(BookRow bookRow) {
    Book book = modelMapper.map(bookRow, Book.class);
    book.setAuthor(authorService.getById(bookRow.getAuthorId())
        .orElseThrow(() -> new EntityConverterException("Ошибка при выборе автора")));
    book.setGenre(genreService.getById(bookRow.getGenreId())
        .orElseThrow(() -> new EntityConverterException("Ошибка при выборе жанра")));
    return book;
  }

  @Override
  public AuthorRow convertAuthorEntityToDto(Author author) {
    return modelMapper.map(author, AuthorRow.class);
  }

  @Override
  public GenreRow convertGenreEntityToDto(Genre genre) {
    return modelMapper.map(genre, GenreRow.class);
  }
}