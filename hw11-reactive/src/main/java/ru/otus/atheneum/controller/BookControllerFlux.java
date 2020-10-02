package ru.otus.atheneum.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.atheneum.dto.AuthorDto;
import ru.otus.atheneum.dto.BookDto;
import ru.otus.atheneum.dto.GenreDto;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.EntityConverter;
import ru.otus.atheneum.service.GenreService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookControllerFlux {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final EntityConverter entityConverter;

  @GetMapping("/api/flux/books")
  public Flux<BookDto> showBookList() {
    List<BookDto> books = entityConverter.convertBookEntitiesToDto(bookService.getAll());
    log.info(books.toString());
    return Flux.fromIterable(books);
  }

  @GetMapping("/api/flux/authors")
  public Flux<AuthorDto> showAuthors() {
    List<AuthorDto> authors = entityConverter.convertAuthorEntitiesToDto(authorService.getAll());
    log.info(authors.toString());
    return Flux.fromIterable(authors);
  }

  @GetMapping("/api/flux/genres")
  public Flux<GenreDto> showGenres() {
    List<GenreDto> genres = entityConverter.convertGenreEntitiesToDto(genreService.getAll());
    log.info(genres.toString());
    return Flux.fromIterable(genres);
  }

  @GetMapping("/api/flux/books/{id}")
  public Mono<BookDto> showBookById(@PathVariable("id") String id) {
    BookDto book = entityConverter.convertBookEntityToDto(bookService.getById(id).orElseThrow());
    log.info(String.format("Возврат книги с id = %s", id));
    return Mono.just(book);
  }

  @DeleteMapping("/api/flux/books/{id}")
  public String deleteBookById(@PathVariable("id") String id) {
    bookService.delete(id);
    log.info(String.format("Удаление книги с id = %s", id));
    return "Book deleted";
  }

  @PostMapping("/api/flux/books")
  public String saveBook(BookDto bookDto) {
    bookService.update(entityConverter.convertBookDtoToEntity(bookDto));
    log.info(String.format("Сохранение книги %s", bookDto));
    return "Book saved";
  }

}
