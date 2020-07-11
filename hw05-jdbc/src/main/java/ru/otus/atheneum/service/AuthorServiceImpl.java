package ru.otus.atheneum.service;

import java.util.ArrayList;
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

  private List<Author> preparedAuthorList = new ArrayList<>();

  @Override
  public List<Author> findByFullNamePart(String fullNamePart) {
    return authorDao.getByFullNamePart(fullNamePart);
  }

  @Override
  public Optional<Author> saveByNameAndGetAuthor(String fullName) {
    return authorDao.insert(fullName);
  }

  @Override
  public void prepareAll() {
    preparedAuthorList = authorDao.getAll();
  }

  @Override
  public List<Author> getPreparedAuthorList() {
    return preparedAuthorList;
  }

  @Override
  public Optional<Author> getAuthorFromPreparedListByPosition(int authorPosition) {
    return Optional.of(preparedAuthorList.get(authorPosition-1));
  }
}
