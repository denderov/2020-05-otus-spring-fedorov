package ru.otus.atheneum.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
  public Optional<Author> insert(String fullName) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    Long id = null;
    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("full_name", fullName);
    try {
      jdbcOperations
          .update("insert into authors (full_name) values (:full_name)", namedParameters,
              keyHolder);
      id = (Long) keyHolder.getKey();
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return id == null ? Optional.empty() : getById(id);
  }

  @Override
  public void deleteById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    try {
      jdbcOperations.update("delete from authors where id = :id", params);
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
  }

}
