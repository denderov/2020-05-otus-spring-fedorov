package ru.otus.atheneum.service;

import java.util.List;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.IOService;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

@Service
@RequiredArgsConstructor
public class AtheneumServiceImpl implements AtheneumService {

  private final IOService ioService;
  private final BookService bookService;
  private final BookFactory bookFactory;
  private final AuthorService authorService;

  private List<Book> booksList;
  private List<Author> authorsList;

  @Override
  public void printAllBooks() {
    booksList = bookService.getAll();
    ioService.println(formatObjectList(booksList));
  }

  @Override
  public void printAllAuthors() {
    authorsList = authorService.getAll();
    ioService.println(formatObjectList(authorsList));
  }

  private <T> String formatObjectList(List<T> allBooks) {
    StringJoiner joiner = new StringJoiner("\n");
    for (int i = 0; i < allBooks.size(); i++) {
      String format = String.format("%d. %s", i + 1, allBooks.get(i).toString());
      joiner.add(format);
    }
    return joiner.toString();
  }
}
