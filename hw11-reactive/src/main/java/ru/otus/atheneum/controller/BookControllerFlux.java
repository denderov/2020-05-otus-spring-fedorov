package ru.otus.atheneum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.atheneum.dao.AuthorReactiveRepository;
import ru.otus.atheneum.dao.BookReactiveRepository;
import ru.otus.atheneum.dao.GenreReactiveRepository;
import ru.otus.atheneum.dto.AuthorDto;
import ru.otus.atheneum.dto.BookDto;
import ru.otus.atheneum.dto.GenreDto;
import ru.otus.atheneum.service.EntityConverter;
import ru.otus.domain.Book;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookControllerFlux {

  private final EntityConverter entityConverter;
  private final AuthorReactiveRepository authorRepository;
  private final BookReactiveRepository bookRepository;
  private final GenreReactiveRepository genreRepository;

  @GetMapping("/api/flux/books")
  public Flux<BookDto> showBookList() {
    Flux<BookDto> books = entityConverter.convertBookEntitiesToDto(bookRepository.findAll());
    log.info(books.toString());
    return books;
  }

  @GetMapping("/api/flux/authors")
  public Flux<AuthorDto> showAuthors() {
    Flux<AuthorDto> authors = entityConverter
        .convertAuthorEntitiesToDto(authorRepository.findAll());
    log.info(authors.toString());
    return authors;
  }

  @GetMapping("/api/flux/genres")
  public Flux<GenreDto> showGenres() {
    Flux<GenreDto> genres = entityConverter.convertGenreEntitiesToDto(genreRepository.findAll());
    log.info(genres.toString());
    return genres;
  }

  @GetMapping("/api/flux/books/{id}")
  public Mono<BookDto> showBookById(@PathVariable("id") String id) {
    Mono<BookDto> book = entityConverter.convertBookEntityToDto(bookRepository.findById(id));
    log.info(String.format("Возврат книги с id = %s", id));
    return book;
  }

  @DeleteMapping("/api/flux/books/{id}")
  public Mono<Void> deleteBookById(@PathVariable("id") String id) {
    return bookRepository.deleteById(id);
  }

  @PostMapping("/api/flux/books")
  public Mono<Book> saveBook(BookDto bookDto) {
    return bookRepository.save(entityConverter.convertBookDtoToEntity(bookDto));
  }

}
