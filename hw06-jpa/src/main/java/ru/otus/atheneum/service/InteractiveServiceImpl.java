package ru.otus.atheneum.service;

import java.util.List;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.common.IOService;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class InteractiveServiceImpl implements InteractiveService {

  private final IOService ioService;
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final CommentService commentService;

  @Override
  public void executeSaveBookProcess() {
    Book bookForSave = new Book();
    ioService.println("Введите название книги");
    String bookTitle = ioService.readLine();
    bookForSave.setTitle(bookTitle);

    Author author = chooseAuthor();
    bookForSave.setAuthor(author);

    Genre genre = chooseGenre();
    bookForSave.setGenre(genre);

    Book savedBook = bookService.save(bookTitle, author, genre)
        .orElseThrow(() -> new InteractiveServiceException(
            String.format("Книга \"%s\" не сохранена!", bookTitle)));
    ioService.println(String.format("Сохранена книга %s", savedBook));
  }

  @Override
  public void executeSaveAuthorProcess() {
    ioService.println("Введите имя автора для сохранения");
    String fullName = ioService.readLine();
    Author savedAuthor = authorService.saveByNameAndGetAuthor(fullName)
        .orElseThrow(() -> new InteractiveServiceException(
            String.format("Автор \"%s\" не сохранен!", fullName)));
    ioService.println(String.format("Сохранен автор %s", savedAuthor));
  }

  @Override
  public void executeSaveGenreProcess() {
    ioService.println("Введите название жанра для сохранения");
    String name = ioService.readLine();
    Genre savedGenre = genreService.saveByNameAndGetGenre(name)
        .orElseThrow(() -> new InteractiveServiceException(
            String.format("Жанр \"%s\" не сохранен!", name)));
    ioService.println(String.format("Сохранен жанр %s", savedGenre));
  }

  @Override
  public void executeSaveCommentProcess() {
    Book bookForComment = chooseBook();

    ioService.println("Введите комментарий");
    String commentText = ioService.readLine();

    commentService.saveAndGetComment(bookForComment, commentText);
  }

  @Override
  public void executeUpdateBookProcess() {

    Book bookForUpdate = chooseBook();

    ioService.println("Введите название книги");
    String bookTitle = ioService.readLine();
    bookForUpdate.setTitle(bookTitle);

    bookForUpdate.setAuthor(chooseAuthor());

    bookForUpdate.setGenre(chooseGenre());

    bookService.update(bookForUpdate);

    ioService.println(String.format("Внесены изменения в книге %s", bookForUpdate));
  }

  @Override
  public void executeUpdateAuthorProcess() {
    Author authorForUpdate = chooseAuthor();

    ioService.println("Введите имя автора");
    String authorFullName = ioService.readLine();
    authorForUpdate.setFullName(authorFullName);

    authorService.update(authorForUpdate);

    ioService.println(String.format("Автор изменен %s", authorForUpdate));
  }

  @Override
  public void executeUpdateGenreProcess() {
    Genre genreForUpdate = chooseGenre();

    ioService.println("Введите название жанра");
    String genreName = ioService.readLine();
    genreForUpdate.setName(genreName);

    genreService.update(genreForUpdate);

    ioService.println(String.format("Жанр изменен %s", genreForUpdate));
  }

  @Override
  public void executeUpdateCommentProcess() {
    Comment commentForUpdate = chooseComment();

    ioService.println("Введите новый текст");
    String commentText = ioService.readLine();
    commentForUpdate.setText(commentText);

    commentService.update(commentForUpdate);
  }

  @Override
  public void executeDeleteBookProcess() {
    Book bookForDelete = chooseBook();

    bookService.delete(bookForDelete);

    ioService.println(String.format("Книга удалена %s", bookForDelete));
  }

  @Override
  public void executeDeleteAuthorProcess() {
    Author authorForDelete = chooseAuthor();

    authorService.delete(authorForDelete);

    ioService.println(String.format("Автор удален %s", authorForDelete));
  }

  @Override
  public void executeDeleteGenreProcess() {
    Genre genreForDelete = chooseGenre();

    genreService.delete(genreForDelete);

    ioService.println(String.format("Жанр удален %s", genreForDelete));
  }

  @Override
  public void executeDeleteCommentProcess() {
    Comment commentForDelete = chooseComment();

    commentService.delete(commentForDelete);

    ioService.println(String.format("Комментарий удален %s", commentForDelete));
  }

  @Override
  public void printAllAuthors() {
    getAuthors();
  }

  @Override
  public void printAllGenres() {
    getGenres();
  }

  @Override
  public void printAllBooks() {
    getBooks();
  }

  @Override
  public void printComments() {
    Book commentedBook = chooseBook();
    List<Comment> comments = commentService.getCommentsByBook(commentedBook);
    ioService.println(formatObjectList(comments));
  }

  private Author chooseAuthor() {
    List<Author> authors = getAuthors();
    ioService.println("Введите порядковый номер автора");
    int authorIndex = getListIndex(authors);
    return authors.get(authorIndex);
  }

  private List<Author> getAuthors() {
    List<Author> authors = authorService.getAll();
    ioService.println(formatObjectList(authors));
    return authors;
  }

  private Genre chooseGenre() {
    List<Genre> genres = getGenres();
    ioService.println("Введите порядковый номер жанра");
    int genreIndex = getListIndex(genres);
    return genres.get(genreIndex);
  }

  private List<Genre> getGenres() {
    List<Genre> genres = genreService.getAll();
    ioService.println(formatObjectList(genres));
    return genres;
  }

  private Book chooseBook() {
    List<Book> books = getBooks();
    ioService.println("Введите порядковый номер книги");
    int bookIndex = getListIndex(books);
    return books.get(bookIndex);
  }

  private Comment chooseComment() {
    Book commentedBook = chooseBook();
    List<Comment> comments = commentService.getCommentsByBook(commentedBook);
    ioService.println(formatObjectList(comments));
    ioService.println("Введите порядковый номер комментария");
    int commentIndex = getListIndex(comments);
    return comments.get(commentIndex);
  }

  private List<Book> getBooks() {
    List<Book> books = bookService.getAll();
    ioService.println(formatObjectList(books));
    return books;
  }

  private <T> int getListIndex(List<T> list) {
    int authorPosition = -1;
    int index = -1;
    boolean hasExceptionOnParse;
    do {
      hasExceptionOnParse = false;
      try {
        authorPosition = Integer.parseInt(ioService.readLine());
        index = authorPosition - 1;
      } catch (NumberFormatException e) {
        e.printStackTrace();
        hasExceptionOnParse = true;
      }
    } while (indexIsIncorrect(list, index) || hasExceptionOnParse);
    return authorPosition - 1;
  }

  private <T> boolean indexIsIncorrect(List<T> list, int index) {
    return index < 0 || index >= list.size();
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