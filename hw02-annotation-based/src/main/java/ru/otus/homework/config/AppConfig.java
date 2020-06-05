package ru.otus.homework.config;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.homework.common.ConsoleService;
import ru.otus.homework.common.IOService;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public IOService ioService() {
    return new ConsoleService(System.in, System.out);
  }

  @Bean
  public IOService ioDaoService(@Value("${csv.name}") String defaultQuizCsv) {
    InputStream inputStream = this.getClass().getResourceAsStream("/" + defaultQuizCsv);
    return new ConsoleService(inputStream, System.out);
  }

}
