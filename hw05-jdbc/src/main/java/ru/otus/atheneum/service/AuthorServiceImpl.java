package ru.otus.atheneum.service;

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
  public Author getByFullName(String fullName) {
    return authorDao.getByFullName(fullName)
        .orElseGet(() -> authorDao.insert(fullName).orElseThrow());
  }

}
