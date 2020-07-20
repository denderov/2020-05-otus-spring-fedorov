package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.common.IOService;
import ru.otus.common.IOServiceImpl;

@Configuration
public class AppConfig {

  @Bean
  public IOService ioService() {
    return new IOServiceImpl(System.in, System.out);
  }
}
