package ru.otus.atheneum.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.atheneum.dto.AuthorRow;
import ru.otus.atheneum.dto.BookRow;
import ru.otus.atheneum.dto.GenreRow;
import ru.otus.atheneum.service.AuthorService;
import ru.otus.atheneum.service.BookService;
import ru.otus.atheneum.service.GenreService;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final ModelMapper modelMapper;

  @Override
  @GetMapping("/")
  public String bookList(Model model) {
    List<BookRow> books = bookService.getAll().stream().map(this::convertEntityToDto)
        .collect(Collectors.toList());
    log.info(books.toString());
    model.addAttribute("books", books);
    return "books";
  }

  @Override
  @GetMapping("/book/delete/{id}")
  public RedirectView deleteBook(Model model, @PathVariable("id") String id) {
    bookService.delete(id);
    log.info(String.format("Удаление книги с id = %s", id));
    return new RedirectView("/", true);
  }

  @Override
  @GetMapping("/book/edit/{id}")
  public String editBook(Model model, @PathVariable("id") String id) {
    Book book = bookService.getById(id)
        .orElseThrow(
            () -> new BookControllerException(
                String.format("Нет книги по переданному идентификатору %s", id)));
    BookRow bookRow = convertEntityToDto(book);
    List<AuthorRow> authors = authorService.getAll().stream().map(this::convertAuthorEntityToDto)
        .collect(Collectors.toList());
    List<GenreRow> genres = genreService.getAll().stream().map(this::convertGenreEntityToDto)
        .collect(Collectors.toList());

    model.addAttribute("book", bookRow);
    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);
    return "book_edit";
  }

  @Override
  @PostMapping("/book/edit")
  public RedirectView savePerson(
      BookRow bookRow,
      Model model
  ) {
    log.info(bookRow.toString());
    bookService.update(convertDtoToEntity(bookRow));
    return new RedirectView("/", true);
  }

  private BookRow convertEntityToDto(Book book) {
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

  private Book convertDtoToEntity(BookRow bookRow) {
    log.info(bookRow.getAuthorId());
//    modelMapper.getConfiguration().setSkipNullEnabled(true);
//    modelMapper.typeMap(BookRow.class,Book.class).addMappings(
//        mapper -> {
//          mapper.<String>map(src -> src.getAuthorId(),
//              (dest,v) -> dest.setAuthor(authorService.getById(v).orElseThrow(
//                    () -> new BookControllerException(
//                        String.format("Не найден автор по переданному идентификатору %s", v)))));
//          mapper.<String>map(src -> src.getGenreId(),
//              (dest,v) -> dest.setGenre(genreService.getById((String) v).orElseThrow(
//                  () -> new BookControllerException(
//                      String.format("Не найден жанр по переданному идентификатору %s", v)))));
//        }
//    );
    Book book = modelMapper.map(bookRow, Book.class);
    book.setAuthor(authorService.getById(bookRow.getAuthorId()).orElseThrow());
    book.setGenre(genreService.getById(bookRow.getGenreId()).orElseThrow());
    return book;
  }

  private AuthorRow convertAuthorEntityToDto(Author author) {
    return modelMapper.map(author, AuthorRow.class);
  }

  private GenreRow convertGenreEntityToDto(Genre genre) {
    return modelMapper.map(genre, GenreRow.class);
  }
}
