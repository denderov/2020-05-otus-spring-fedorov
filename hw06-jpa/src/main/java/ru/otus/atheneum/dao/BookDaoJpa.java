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
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Repository
@Slf4j
public class BookDaoJpa implements BookDao {

  @PersistenceContext
  EntityManager em;

  @Override
  public Optional<Book> getById(long id) {
    Book nullableBook = em.find(Book.class, id);
    log.info(String.format("getting book from db: %s", nullableBook));
    return Optional.ofNullable(nullableBook);
  }

  @Override
  public List<Book> getAll() {
    TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
    List<Book> books = query.getResultList();
    log.info(String.format("getting books from db: %s", books));
    return books;
  }

  @Override
  public void delete(Book book) {
    Query query = em.createQuery("delete from Book where id = :id");
    query.setParameter("id", book.getId());
    int count = query.executeUpdate();
    log.info(String
        .format("delete book from db: %s. count of deleted rows: %d.", book.toString(), count));
  }

  @Override
  public Optional<Book> insert(String title, Author author, Genre genre) {
    Book book = new Book();
    book.setTitle(title);
    book.setAuthor(author);
    book.setGenre(genre);
    em.persist(book);
    log.info(String.format("inserted book: %s", book));
    return Optional.of(book);
  }

  @Override
  public void update(Book book) {
    em.merge(book);
    log.info(String.format("updated book: %s.", book.toString()));
  }
}
