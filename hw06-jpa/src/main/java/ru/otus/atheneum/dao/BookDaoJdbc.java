package ru.otus.atheneum.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

//@Repository
@RequiredArgsConstructor
@Slf4j
public class BookDaoJdbc implements BookDao {

  private final NamedParameterJdbcOperations jdbcOperations;
  private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
    Author author = new Author(rs.getLong("author_id"), rs.getString("author_full_name"));
    Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("genre_name"));
    return new Book(rs.getLong("id"), rs.getString("title"), author, genre);
  };

  @Override
  public Optional<Book> getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    Book nullableBook = DataAccessUtils.singleResult(jdbcOperations.query(
        "select b.id, b.title, "
            + "b.author_id, a.full_name as author_full_name, "
            + "b.genre_id, g.name as genre_name "
            + "from books b "
            + "left join authors a on b.author_id = a.id "
            + "left join genres g on b.genre_id = g.id "
            + "where b.id=:id",
        params, bookRowMapper));
    log.info(String.format("getting book from db: %s", nullableBook));
    return Optional.ofNullable(nullableBook);
  }

  @Override
  public List<Book> getAll() {
    List<Book> books = jdbcOperations.query(
        "select b.id, b.title, "
            + "b.author_id, a.full_name as author_full_name, "
            + "b.genre_id, g.name as genre_name "
            + "from books b "
            + "left join authors a on b.author_id = a.id "
            + "left join genres g on b.genre_id = g.id ",
        bookRowMapper);
    log.info(String.format("getting books from db: %s", books));
    return books;
  }

  @Override
  public void delete(Book book) {
    Map<String, Object> params = Collections.singletonMap("id", book.getId());
    int count = jdbcOperations.update("delete from books where id = :id", params);
    log.info(String
        .format("delete book from db: %s. count of deleted rows: %d.", book.toString(), count));
  }

  @Override
  public Optional<Book> insert(String title, Author author, Genre genre) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    Long id;

    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("title", title)
        .addValue("author_id", author.getId())
        .addValue("genre_id", genre.getId());

    jdbcOperations.update(
        "insert into books (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
        namedParameters, keyHolder);
    id = (Long) keyHolder.getKey();
    Optional<Book> book = id == null ? Optional.empty() : getById(id);
    log.info(String.format("inserted book: %s", book));
    return book;
  }

  @Override
  public void update(Book book) {
    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("id", book.getId())
        .addValue("title", book.getTitle())
        .addValue("author_id", book.getAuthor().getId())
        .addValue("genre_id", book.getGenre().getId());

    int count = jdbcOperations.update(
        "update books set "
            + "title = :title, "
            + "author_id = :author_id, "
            + "genre_id = :genre_id "
            + "where id = :id",
        namedParameters);
    log.info(
        String.format("updated book: %s. count of updated books: %d.", book.toString(), count));
  }
}
