package ru.otus.homework.config;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CsvPropertiesTest {

  @Autowired
  private CsvProperties csvProperties;

  @Test
  void readingPropertyTest() {
    assertThat(csvProperties.getFileName()).isEqualTo("correct_test.csv");
  }

}
