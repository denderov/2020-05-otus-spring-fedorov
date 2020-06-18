package ru.otus.homework.common;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import ru.otus.homework.logging.Loggable;

public class IOServiceImpl implements IOService {

  private final PrintStream printStream;
  private final Scanner scanner;

  public IOServiceImpl(InputStream inputStream, PrintStream printStream) {
    this.printStream = printStream;
    this.scanner = new Scanner(inputStream);
  }

  @Loggable
  @Override
  public void println(String line) {
    printStream.println(line);
  }

  @Override
  public String nextLine() {
    return scanner.nextLine();
  }

  @Override
  public int nextInt() {
    return scanner.nextInt();
  }

}
