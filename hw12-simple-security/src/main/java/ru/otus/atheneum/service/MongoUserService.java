package ru.otus.atheneum.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.atheneum.dao.UsersRepository;
import ru.otus.domain.Users;

@Service
@RequiredArgsConstructor
public class MongoUserService implements UserDetailsService {

  private final UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Users user = usersRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    List<SimpleGrantedAuthority> authorities = Arrays
        .asList(new SimpleGrantedAuthority(user.getRole()));

    return new User(user.getUsername(), user.getPassword(), authorities);
  }
}
