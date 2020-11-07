package ru.otus.atheneum.dao;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

  Optional<Users> findByUsername(String id);

}

