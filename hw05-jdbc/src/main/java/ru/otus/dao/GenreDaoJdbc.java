package ru.otus.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
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
    jdbcOperations.update("insert into genres (id, name) values (:id,:name)", params);
  }

  @Override
  public void deleteById(long id) {
    Map<String, Object> params = Collections.singletonMap("id", id);
    jdbcOperations.update("delete from genres where id = :id", params);
  }
}
