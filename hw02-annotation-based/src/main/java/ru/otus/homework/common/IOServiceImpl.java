package ru.otus.homework.common;

import java.io.InputStream;
import java.io.PrintStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IOServiceImpl implements IOService {

  private final InputStream inputStream;
  private final PrintStream printStream;

  @Override
  public InputStream in() {
    return inputStream;
  }

  @Override
  public PrintStream out() {
    return printStream;
  }
}
