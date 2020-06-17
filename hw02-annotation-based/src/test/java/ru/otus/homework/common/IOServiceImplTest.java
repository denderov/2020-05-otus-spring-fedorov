package ru.otus.homework.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс IOServiceImpl")
class IOServiceImplTest {

  private ByteArrayOutputStream testOut;

  @DisplayName("корректно реализует метод println()")
  @Test
  void println() {
    testOut = new ByteArrayOutputStream();
    IOService ioService = new IOServiceImpl(System.in, new PrintStream(testOut));
    ioService.println(TestHelper.TEST_MESSAGE);
    assertThat(testOut.toString()).contains(TestHelper.TEST_MESSAGE);
  }
}