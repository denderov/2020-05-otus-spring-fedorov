package ru.otus.atheneum.dao;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

@Repository
@Slf4j
public class CommentDaoJpa implements CommentDao {

  @PersistenceContext
  EntityManager em;

  @Override
  public Optional<Comment> getById(long commentId) {
    Comment nullableComment = em.find(Comment.class, commentId);
    log.info(String.format("getting comment from db: %s", nullableComment));
    return Optional.ofNullable(nullableComment);
  }

  @Override
  public List<Comment> getAll() {
    TypedQuery<Comment> query = em.createQuery("select g from Comment g", Comment.class);
    List<Comment> comments = query.getResultList();
    log.info(String.format("getting comments from db: %s", comments));
    return comments;
  }

  @Override
  public void delete(Comment comment) {
    Query query = em.createQuery("delete from Comment where id = :id");
    query.setParameter("id", comment.getId());
    int count = query.executeUpdate();
    log.info(String
        .format("delete book from db: %s. count of deleted rows: %d.", comment.toString(), count));
  }

  @Override
  public Optional<Comment> insert(Book book, String text) {
    Comment comment = new Comment();
    comment.setDateTime(LocalDateTime.now());
    comment.setBook(book);
    comment.setText(text);
    em.persist(comment);
    log.info(String.format("inserted comment to db: %s", comment.toString()));
    return Optional.of(comment);
  }

  @Override
  public void update(Comment comment) {
    em.merge(comment);
    log.info(String.format("updated comment: %s", comment.toString()));
  }
}
