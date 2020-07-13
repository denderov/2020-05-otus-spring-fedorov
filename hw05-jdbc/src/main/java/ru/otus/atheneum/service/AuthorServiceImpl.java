package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.AuthorDao;
import ru.otus.domain.Author;

@Service
@RequiredArgsConstructor()
public class AuthorServiceImpl implements AuthorService {

  private final AuthorDao authorDao;

  @Override
  public Optional<Author> saveByNameAndGetAuthor(String fullName) {
    return authorDao.insert(fullName);
  }

  @Override
  public void update(Author authorForUpdate) {
    authorDao.update(authorForUpdate);
  }

  @Override
  public void delete(Author author) {
    authorDao.delete(author);
  }

  @Override
  public List<Author> getAll() {
    return authorDao.getAll();
  }
}
