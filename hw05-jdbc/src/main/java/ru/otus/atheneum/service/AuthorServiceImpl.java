package ru.otus.atheneum.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.AuthorDao;
import ru.otus.common.IOService;
import ru.otus.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final IOService ioService;
  private final AuthorDao authorDao;

  @Override
  public List<Author> getByFullNamePart(String fullNamePart) {
    return authorDao.getByFullNamePart(fullNamePart);
  }

  @Override
  public Optional<Author> saveByNameAndGetAuthor(String fullName) {
    return authorDao.insert(fullName);
  }

}
