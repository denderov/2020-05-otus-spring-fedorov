package ru.otus.atheneum.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

@Repository
@Slf4j
public class GenreDaoJpa implements GenreDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Optional<Genre> getById(long id) {
    Genre nullableGenre = em.find(Genre.class, id);
    log.info(String.format("getting genre from db: %s", nullableGenre));
    return Optional.ofNullable(nullableGenre);
  }

  @Override
  public List<Genre> getAll() {
    TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
    List<Genre> genres = query.getResultList();
    log.info(String.format("getting genres from db: %s", genres));
    return genres;
  }

  @Override
  public void delete(Genre genre) {
    Query query = em.createQuery("delete from Genre where id = :id");
    query.setParameter("id", genre.getId());
    int count = query.executeUpdate();
    log.info(String
        .format("delete book from db: %s. count of deleted rows: %d.", genre.toString(), count));
  }

  @Override
  public Optional<Genre> insert(String name) {
    Genre genre = new Genre();
    genre.setName(name);
    em.persist(genre);
    log.info(String.format("inserted genre to db: %s", genre.toString()));
    return Optional.of(genre);
  }

  @Override
  public void update(Genre genre) {
    em.merge(genre);
    log.info(String.format("updated genre: %s", genre.toString()));
  }
}
