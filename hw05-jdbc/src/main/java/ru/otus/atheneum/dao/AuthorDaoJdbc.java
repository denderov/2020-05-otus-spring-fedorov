package ru.otus.atheneum.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@Slf4j
public class AuthorDaoJdbc implements AuthorDao {

  private final NamedParameterJdbcOperations jdbcOperations;
  private final RowMapper<Author> authorRowMapper =
      (rs, rowNum) -> new Author(rs.getLong("id"), rs.getString("full_name"));

  @Override
  public Optional<Author> getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    Author nullableAuthor;
    nullableAuthor = DataAccessUtils.singleResult(jdbcOperations
            .query("select * from authors where id = :id", params, authorRowMapper));
    log.info(String.format("getting author from db: %s", nullableAuthor));
    return Optional.ofNullable(nullableAuthor);
  }

  @Override
  public List<Author> getAll() {
    List<Author> authors = jdbcOperations.query("select id, full_name from authors", authorRowMapper);
    log.info(String.format("getting authors from db: %s", authors));
    return authors;
  }

  @Override
  public Optional<Author> insert(String fullName) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    Long id;
    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("full_name", fullName);
    jdbcOperations
            .update("insert into authors (full_name) values (:full_name)", namedParameters,
                    keyHolder);
    id = (Long) keyHolder.getKey();
    Optional<Author> optionalAuthor = id == null ? Optional.empty() : getById(id);
    log.info(String.format("inserted author to db: %s", optionalAuthor.toString()));
    return optionalAuthor;
  }

  @Override
  public void delete(Author author) {
    Map<String, Object> params = Collections.singletonMap("id", author.getId());
    int count = jdbcOperations.update("delete from authors where id = :id", params);
    log.info(String.format("delete author from db: %s. count of deleted rows: %d", author.toString(), count));
  }

  @Override
  public List<Author> getByFullNamePart(String fullNamePart) {
    Map<String, Object> params = Collections
        .singletonMap("fullNameMask", String.format("%%%s%%", fullNamePart));
    List<Author> authors = jdbcOperations
            .query("select * from authors where full_name like :fullNameMask", params,
                    authorRowMapper);
    log.info(String.format("getting authors from db: %s", authors));
    return authors;
  }

}
