package ru.otus.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.h2.domain.H2Author;
import ru.otus.h2.domain.H2Book;
import ru.otus.h2.domain.H2Genre;
import ru.otus.h2.service.H2AuthorService;
import ru.otus.h2.service.H2BookService;
import ru.otus.h2.service.H2GenreService;
import ru.otus.mongo.domain.MongoAuthor;
import ru.otus.mongo.domain.MongoBook;
import ru.otus.mongo.domain.MongoGenre;
import ru.otus.mongo.service.MongoAuthorService;
import ru.otus.mongo.service.MongoBookService;
import ru.otus.mongo.service.MongoGenreService;

import java.util.List;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class InteractiveServiceImpl implements InteractiveService {

  private final IOService ioService;
  private final MongoAuthorService mongoAuthorService;
  private final MongoBookService mongoBookService;
  private final MongoGenreService mongoGenreService;
  private final H2AuthorService h2AuthorService;
  private final H2BookService h2BookService;
  private final H2GenreService h2GenreService;

  @Override
  public void printAllMongoAuthors() {
    List<MongoAuthor> mongoAuthors = mongoAuthorService.getAll();
    ioService.println(formatObjectList(mongoAuthors));
  }

  @Override
  public void printAllMongoBooks() {
    List<MongoBook> mongoBooks = mongoBookService.getAll();
    ioService.println(formatObjectList(mongoBooks));
  }

  @Override
  public void printAllMongoGenres() {
    List<MongoGenre> mongoGenres = mongoGenreService.getAll();
    ioService.println(formatObjectList(mongoGenres));
  }

  @Override
  public void printAllH2Authors() {
    List<H2Author> h2Authors = h2AuthorService.getAll();
    ioService.println(formatObjectList(h2Authors));
  }

  @Override
  public void printAllH2Books() {
    List<H2Book> h2Books = h2BookService.getAll();
    ioService.println(formatObjectList(h2Books));
  }

  @Override
  public void printAllH2Genres() {
    List<H2Genre> h2Genres = h2GenreService.getAll();
    ioService.println(formatObjectList(h2Genres));
  }

  private <T> String formatObjectList(List<T> ObjectList) {
    StringJoiner joiner = new StringJoiner("\n");
    for (int i = 0; i < ObjectList.size(); i++) {
      String format = String.format("%d. %s", i + 1, ObjectList.get(i).toString());
      joiner.add(format);
    }
    return joiner.toString();
  }
}