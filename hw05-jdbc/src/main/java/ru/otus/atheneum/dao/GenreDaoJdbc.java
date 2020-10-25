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
import ru.otus.domain.Genre;

@Repository
@RequiredArgsConstructor
@Slf4j
public class GenreDaoJdbc implements GenreDao {

  private final NamedParameterJdbcOperations jdbcOperations;
  private final RowMapper<Genre> genreRowMapper =
      (rs, rowNum) -> new Genre(rs.getLong("id"), rs.getString("name"));

  @Override
  public Optional<Genre> getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    Genre nullableGenre = DataAccessUtils.singleResult(jdbcOperations
        .query("select id, name from genres where id = :id", params, genreRowMapper));
    log.info(String.format("getting genre from db: %s", nullableGenre));
    return Optional.ofNullable(nullableGenre);
  }

  @Override
  public List<Genre> getAll() {
    List<Genre> genres = jdbcOperations.query("select id, name from genres", genreRowMapper);
    log.info(String.format("getting genres from db: %s", genres));
    return genres;
  }

  @Override
  public void delete(Genre genre) {
    Map<String, Object> params = Collections.singletonMap("id", genre.getId());
    int count = jdbcOperations.update("delete from genres where id = :id", params);
    log.info(String.format("delete book from db: %s. count of deleted rows: %d.", genre.toString(), count));
  }

  @Override
  public Optional<Genre> insert(String name) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    Long id = null;
    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("name", name);
    try {
      jdbcOperations
          .update("insert into genres (name) values (:name)", namedParameters,
              keyHolder);
      id = (Long) keyHolder.getKey();
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return id == null ? Optional.empty() : getById(id);
  }

  @Override
  public void update(Genre genre) {
    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("id", genre.getId())
        .addValue("name", genre.getName());

    int count = jdbcOperations.update(
        "update genres set "
            + "name = :name "
            + "where id = :id",
        namedParameters);
    log.info(String
        .format("updated genre: %s. count of updated genres: %d.", genre.toString(), count));
  }
}
