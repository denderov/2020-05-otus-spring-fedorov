package ru.otus.homework.config;

import java.util.Locale;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "quiz")
@Data
public class QuizProperties {

  private int questionCount;
  private int passPercent;
  private Locale locale;
  private String name;

  public String getCsvFileName() {
    return String.format("%s_%s.csv", this.getName(), this.getLocale());
  }
}
