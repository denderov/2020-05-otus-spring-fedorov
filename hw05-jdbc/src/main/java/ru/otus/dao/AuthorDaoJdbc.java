package ru.otus.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

  private final NamedParameterJdbcOperations jdbcOperations;
  private final RowMapper<Author> authorRowMapper =
      (rs, rowNum) -> new Author(rs.getLong("id"), rs.getString("full_name"));

  public AuthorDaoJdbc(
      NamedParameterJdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public Optional<Author> getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    Author nullableAuthor = DataAccessUtils.singleResult(jdbcOperations
        .query("select * from authors where id = :id", params, authorRowMapper));
    return Optional.ofNullable(nullableAuthor);
  }

  @Override
  public List<Author> getAll() {
    return jdbcOperations.query("select id, full_name from authors", authorRowMapper);
  }

  @Override
  public void insert(Author author) {
    Map<String, Object> params = Map.of("id", author.getId(), "full_name", author.getFullName());
    jdbcOperations.update("insert into authors (id, full_name) values (:id, :full_name)", params);
  }

  @Override
  public void deleteById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    jdbcOperations.update("delete from authors where id = :id", params);
  }
}
