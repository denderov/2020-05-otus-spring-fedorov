package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.homework.common.IOService;
import ru.otus.homework.common.IOServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public IOService ioService() {
    return new IOServiceImpl(System.in, System.out);
  }

}
