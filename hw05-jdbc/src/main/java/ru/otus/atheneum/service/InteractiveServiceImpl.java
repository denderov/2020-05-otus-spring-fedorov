package ru.otus.atheneum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.IOService;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class InteractiveServiceImpl implements InteractiveService {

  private final IOService ioService;
  private final AtheneumService atheneumService;

  @Override
  public void bookSaver() {
    atheneumService.initBook();
    ioService.println("Введите название книги");
    String bookTitle = ioService.readLine();
    atheneumService.setBookTitle(bookTitle);

    Author author = chooseAuthor();
    atheneumService.setBookAuthor(author);

    Genre genre = chooseGenre();
    atheneumService.setBookGenre(genre);

    atheneumService.saveBook();
  }

  @Override
  public void authorSaver() {
    ioService.println("Введите имя автора для сохранения");
    String fullName = ioService.readLine();
    atheneumService.saveAuthorByName(fullName);
  }

  @Override
  public void genreSaver() {
    ioService.println("Введите название жанра для сохранения");
    String name = ioService.readLine();
    atheneumService.saveGenreByName(name);
  }

  @Override
  public void bookUpdater() {

    Book bookForUpdate = chooseBook();

    ioService.println("Введите название книги");
    String bookTitle = ioService.readLine();
    bookForUpdate.setTitle(bookTitle);

    bookForUpdate.setAuthor(chooseAuthor());

    bookForUpdate.setGenre(chooseGenre());

    atheneumService.updateBook(bookForUpdate);

    ioService.println(String.format("Внесены изменения в книге %s", bookForUpdate));
  }

  @Override
  public void authorUpdater() {
    Author authorForUpdate = chooseAuthor();

    ioService.println("Введите имя автора");
    String authorFullName = ioService.readLine();
    authorForUpdate.setFullName(authorFullName);

    atheneumService.updateAuthor(authorForUpdate);

    ioService.println(String.format("Автор изменен %s", authorForUpdate));
  }

  @Override
  public void genreUpdater() {
    Genre genreForUpdate = chooseGenre();

    ioService.println("Введите название жанра");
    String genreName = ioService.readLine();
    genreForUpdate.setName(genreName);

    atheneumService.updateGenre(genreForUpdate);

    ioService.println(String.format("Жанр изменен %s", genreForUpdate));
  }

  @Override
  public void bookDeleter() {
    Book bookForDelete = chooseBook();

    atheneumService.deleteBook(bookForDelete);

    ioService.println(String.format("Книга удалена %s", bookForDelete));
  }

  @Override
  public void authorDeleter() {
    Author authorForDelete = chooseAuthor();

    atheneumService.deleteAuthor(authorForDelete);

    ioService.println(String.format("Автор удален %s", authorForDelete));
  }

  @Override
  public void genreDeleter() {
    Genre genreForDelete = chooseGenre();

    atheneumService.deleteGenre(genreForDelete);

    ioService.println(String.format("Жанр удален %s", genreForDelete));
  }

  private Book chooseBook() {
    atheneumService.printAllBooks();
    ioService.println("Введите порядковый номер книги");
    int bookPosition = Integer.parseInt(ioService.readLine());
    return atheneumService
        .getBookFromPreparedListByPosition(bookPosition).orElseThrow();
  }

  private Author chooseAuthor() {
    atheneumService.printAllAuthors();
    ioService.println("Введите порядковый номер автора");
    int authorPosition = Integer.parseInt(ioService.readLine());
    return atheneumService.getAuthorFromPreparedListByPosition(authorPosition)
        .orElseThrow();
  }

  private Genre chooseGenre() {
    atheneumService.printAllGenres();
    ioService.println("Введите порядковый номер жанра");
    int genrePosition = Integer.parseInt(ioService.readLine());
    return atheneumService.getGenreFromPreparedListByPosition(genrePosition)
        .orElseThrow();
  }
}