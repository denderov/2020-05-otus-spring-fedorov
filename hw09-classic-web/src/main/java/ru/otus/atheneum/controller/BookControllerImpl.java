package ru.otus.atheneum.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.atheneum.dto.BookRow;
import ru.otus.atheneum.service.BookService;
import ru.otus.domain.Book;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

  private final BookService bookService;
  private final ModelMapper modelMapper;

  @Override
  @GetMapping("/")
  public String bookList(Model model) {
    List<BookRow> books = bookService.getAll().stream().map(this::convertToDto)
        .collect(Collectors.toList());
    log.info(books.toString());
    model.addAttribute("books", books);
    return "books";
  }

  private BookRow convertToDto(Book book) {
    BookRow bookRow = modelMapper.map(book, BookRow.class);
    bookRow.setAuthor(book.getAuthor().getFullName());
    bookRow.setGenre(book.getGenre().getName());
    return bookRow;
  }
}
