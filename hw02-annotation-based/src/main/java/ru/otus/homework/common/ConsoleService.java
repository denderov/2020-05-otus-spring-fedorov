package ru.otus.homework.common;

import java.io.PrintStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsoleService implements IOService {

  private final PrintStream printStream;

  @Override
  public void println(String x) {
    printStream.println(x);
  }
}
