package ru.otus.homework.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс ConsoleServiceImpl")
class ConsoleServiceTest {

  private ByteArrayOutputStream testOut;

  @DisplayName("корректно реализует метод println()")
  @Test
  void println() {
    testOut = new ByteArrayOutputStream();
    IOService IOService = new ConsoleService(new PrintStream(testOut));
    IOService.println(TestHelper.TEST_MESSAGE);
    assertThat(testOut.toString()).contains(TestHelper.TEST_MESSAGE);
  }
}