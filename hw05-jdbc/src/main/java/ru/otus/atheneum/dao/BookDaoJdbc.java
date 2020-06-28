package ru.otus.atheneum.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Repository
public class BookDaoJdbc implements BookDao {

  private final NamedParameterJdbcOperations jdbcOperations;
  private final AuthorDao authorDao;
  private final GenreDao genreDao;
  private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
    Author author = new Author(rs.getLong("author_id"), rs.getString("author_full_name"));
    Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("genre_name"));
    return new Book(rs.getLong("id"), rs.getString("title"), author, genre);
  };

  public BookDaoJdbc(
      NamedParameterJdbcOperations jdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
    this.jdbcOperations = jdbcOperations;
    this.authorDao = authorDao;
    this.genreDao = genreDao;
  }

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
    return Optional.ofNullable(nullableBook);
  }

  @Override
  public List<Book> getAll() {
    return jdbcOperations.query(
        "select b.id, b.title, "
            + "b.author_id, a.full_name as author_full_name, "
            + "b.genre_id, g.name as genre_name "
            + "from books b "
            + "left join authors a on b.author_id = a.id "
            + "left join genres g on b.genre_id = g.id ",
        bookRowMapper);
  }

  @Override
  public void insert(Book book) {

    Author author = book.getAuthor();
    if (authorDao.getById(author.getId()).isEmpty()) {
      authorDao.insert(author.getFullName());
    }

    Genre genre = book.getGenre();
    if (genreDao.getById(genre.getId()).isEmpty()) {
      genreDao.insert(genre);
    }

    Map<String, Object> params = Map.of(
        "id", book.getId(),
        "title", book.getTitle(),
        "author_id", author.getId(),
        "genre_id", genre.getId()
    );
    jdbcOperations.update(
        "insert into books (id, title, author_id, genre_id) values (:id, :title, :author_id, :genre_id)",
        params);
  }

  @Override
  public void delete(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    jdbcOperations.update("delete from books where id = :id", params);
  }
}
