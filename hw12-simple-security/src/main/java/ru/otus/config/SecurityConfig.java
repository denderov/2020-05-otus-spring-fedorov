package ru.otus.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.atheneum.service.MongoUserService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//  @Autowired
//  private AccessDeniedHandler accessDeniedHandler;

  private final MongoUserService mongoUserService;

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/", "/webjars/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/book/edit")
        .hasAnyAuthority("ADMIN", "USER")
        .antMatchers("/**")
        .hasAuthority("ADMIN")
        .and()
        .formLogin()
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  @SuppressWarnings("deprecation")
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(mongoUserService);
  }
}
