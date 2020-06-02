package ru.otus.homework.common;

import java.io.PrintStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsoleServiceImpl implements ConsoleService {

  private final PrintStream printStream;

  ConsoleServiceImpl() {
    printStream = System.out;
  }

  @Override
  public void println(String x) {
    printStream.println(x);
  }
}
