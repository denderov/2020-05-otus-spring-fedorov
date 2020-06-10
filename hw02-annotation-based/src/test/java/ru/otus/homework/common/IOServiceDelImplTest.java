package ru.otus.homework.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.TestHelper;

@DisplayName("Класс IOServiceImpl")
class IOServiceDelImplTest {

  private ByteArrayOutputStream testOut;

  @DisplayName("корректно реализует метод println()")
  @Test
  void println() {
    testOut = new ByteArrayOutputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    IOService ioService = new IOServiceImpl(reader, new PrintStream(testOut));
    ioService.println(TestHelper.TEST_MESSAGE);
    assertThat(testOut.toString()).contains(TestHelper.TEST_MESSAGE);
  }
}