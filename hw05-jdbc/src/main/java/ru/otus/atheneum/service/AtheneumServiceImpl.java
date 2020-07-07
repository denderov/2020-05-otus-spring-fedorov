package ru.otus.atheneum.service;

import java.util.List;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.IOService;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class AtheneumServiceImpl implements AtheneumService {

  private final IOService ioService;
  private final BookService bookService;
  private final BookFactory bookFactory;
  private final AuthorService authorService;
  private final GenreService genreService;

  private List<Book> bookList;
  private List<Author> authorList;
  private List<Genre> genreList;

  @Override
  public void printAllBooks() {
    bookList = bookService.getAll();
    ioService.println(formatObjectList(bookList));
  }

  @Override
  public void printAllAuthors() {
    authorList = authorService.getAll();
    ioService.println(formatObjectList(authorList));
  }

  @Override
  public void printAllGenres() {
    genreList = genreService.getAll();
    ioService.println(formatObjectList(genreList));
  }

  @Override
  public void saveBook() {
    bookFactory.createBook();
  }

  @Override
  public void setBookTitle(String bookTitle) {
    bookFactory.setTitle(bookTitle);
  }

  @Override
  public void setBookAuthor(Author author) {
    bookFactory.setAuthor(author);
  }

  @Override
  public void setBookGenre(Genre genre) {
    bookFactory.setGenre(genre);
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
