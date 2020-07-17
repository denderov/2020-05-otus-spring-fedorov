package ru.otus.atheneum.service;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.atheneum.dao.AuthorRepository;
import ru.otus.domain.Author;

@Service
@RequiredArgsConstructor()
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  @Transactional
  public Optional<Author> saveByNameAndGetAuthor(String fullName) {
    Author authorForSave = new Author();
    authorForSave.setFullName(fullName);
    return Optional.of(authorRepository.save(authorForSave));
  }

  @Override
  @Transactional
  public void update(Author authorForUpdate) {
    authorRepository.save(authorForUpdate);
  }

  @Override
  @Transactional
  public void delete(Author author) {
    authorRepository.delete(author);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Author> getAll() {
    return Lists.newArrayList(authorRepository.findAll());
  }
}
