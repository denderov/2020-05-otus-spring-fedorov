package ru.otus.atheneum.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

@Repository
@Slf4j
public class AuthorDaoJpa implements AuthorDao {

  @PersistenceContext
  EntityManager em;

  @Override
  public Optional<Author> getById(long id) {
    Author nullableAuthor = em.find(Author.class, id);
    log.info(String.format("getting author from db: %s", nullableAuthor));
    return Optional.ofNullable(nullableAuthor);
  }

  @Override
  public List<Author> getAll() {
    TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
    List<Author> authors = query.getResultList();
    log.info(String.format("getting authors from db: %s", authors));
    return authors;
  }

  @Override
  public Optional<Author> insert(String fullName) {
    Author author = new Author();
    author.setFullName(fullName);
    em.persist(author);
    log.info(String.format("inserted author to db: %s", author.toString()));
    return Optional.of(author);
  }

  @Override
  public void delete(Author author) {
    Query query = em.createQuery("delete from Author where id = :id");
    query.setParameter("id", author.getId());
    int count = query.executeUpdate();
    log.info(String
        .format("delete author from db: %s. count of deleted rows: %d", author.toString(), count));
  }

  @Override
  public void update(Author author) {
    Query query = em.createQuery("update Author set fullName = :fullName where id = :id");
    query.setParameter("id", author.getId()).setParameter("fullName", author.getFullName());
    int count = query.executeUpdate();
    log.info(String
        .format("updated author: %s. count of updated authors: %d.", author.toString(), count));
  }
}
