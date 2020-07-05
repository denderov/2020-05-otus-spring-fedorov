package ru.otus.atheneum.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.IOService;
import ru.otus.domain.Book;

@Service
@RequiredArgsConstructor
public class AtheneumServiceImpl implements AtheneumService {

  private final IOService ioService;
  private final BookService bookService;

  @Override
  public void printAllBooks() {
    List<Book> allBooks = bookService.getAll();
    ioService.println(formatBooks(allBooks));
  }

  private String formatBooks(List<Book> allBooks) {
    return IntStream.range(0, allBooks.size())
        .mapToObj(i -> String.format("%d. %s", i + 1, allBooks.get(i).toString()))
        .collect(Collectors.joining("\n"));
  }
}
