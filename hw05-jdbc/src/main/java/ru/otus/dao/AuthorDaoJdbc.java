package ru.otus.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
  public Author getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    return jdbcOperations
        .queryForObject("select * from authors where id = :id", params, authorRowMapper);
  }

  @Override
  public List<Author> getAll() {
    return jdbcOperations.query("select id, full_name from authors", authorRowMapper);
  }
}
