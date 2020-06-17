package ru.otus.homework.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.common.IOService;
import ru.otus.homework.common.IOServiceImpl;
import ru.otus.homework.quiz.dao.QuizDao;
import ru.otus.homework.quiz.dao.QuizDaoCsv;

@Configuration
public class AppConfig {

  @Bean
  public IOService ioService() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    return new IOServiceImpl(reader, System.out);
  }
  @Bean
  public QuizDao quizDao(QuizProperties quizProperties, MessageSource messageSource) {

    String csvFileName = String
        .format("%s_%s.csv", quizProperties.getName(), quizProperties.getLocale());
    return new QuizDaoCsv(csvFileName);
  }
}
