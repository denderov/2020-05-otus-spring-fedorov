package ru.otus.atheneum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dto.AuthorDto;
import ru.otus.atheneum.dto.BookDto;
import ru.otus.atheneum.dto.GenreDto;
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
  public BookDto convertBookEntityToDto(Book book) {
    log.info(book.toString());
    modelMapper.typeMap(Book.class, BookDto.class).addMappings(
        mapper -> {
          mapper.map(src -> src.getAuthor().getFullName(), BookDto::setAuthor);
          mapper.map(src -> src.getGenre().getName(), BookDto::setGenre);
          mapper.map(src -> src.getAuthor().getId(), BookDto::setAuthorId);
          mapper.map(src -> src.getGenre().getId(), BookDto::setGenreId);
        }
    );
    return modelMapper.map(book, BookDto.class);
  }

  @Override
  public Book convertBookDtoToEntity(BookDto bookDto) {
    Book book = modelMapper.map(bookDto, Book.class);
    book.setAuthor(authorService.getById(bookDto.getAuthorId())
        .orElseThrow(() -> new EntityConverterException("Ошибка при выборе автора")));
    book.setGenre(genreService.getById(bookDto.getGenreId())
        .orElseThrow(() -> new EntityConverterException("Ошибка при выборе жанра")));
    return book;
  }

  @Override
  public AuthorDto convertAuthorEntityToDto(Author author) {
    return modelMapper.map(author, AuthorDto.class);
  }

  @Override
  public GenreDto convertGenreEntityToDto(Genre genre) {
    return modelMapper.map(genre, GenreDto.class);
  }
}