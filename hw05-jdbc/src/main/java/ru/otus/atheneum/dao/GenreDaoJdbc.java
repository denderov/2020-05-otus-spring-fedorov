package ru.otus.atheneum.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class GenreDaoJdbc implements GenreDao {

  private final NamedParameterJdbcOperations jdbcOperations;
  private final RowMapper<Genre> genreRowMapper =
      (rs, rowNum) -> new Genre(rs.getLong("id"), rs.getString("name"));

  @Override
  public Optional<Genre> getById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    Genre nullableGenre = DataAccessUtils.singleResult(jdbcOperations
        .query("select id, name from genres where id = :id", params, genreRowMapper));
    return Optional.ofNullable(nullableGenre);
  }

  @Override
  public List<Genre> getAll() {
    return jdbcOperations.query("select id, name from genres", genreRowMapper);
  }

  @Override
  public void insert(Genre genre) {
    Map<String, Object> params = Map.of(
        "id", genre.getId(),
        "name", genre.getName()
    );
    try {
      jdbcOperations.update("insert into genres (id, name) values (:id,:name)", params);
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    try {
      jdbcOperations.update("delete from genres where id = :id", params);
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Genre> getByNamePart(String genreName) {
    Map<String, Object> params = Collections
        .singletonMap("fullNameMask", String.format("%%%s%%", genreName));
    return jdbcOperations
        .query("select * from genres where name like :fullNameMask", params,
            genreRowMapper);
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
}
