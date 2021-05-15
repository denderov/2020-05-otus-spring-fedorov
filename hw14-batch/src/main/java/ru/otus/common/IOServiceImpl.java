package ru.otus.common;

import java.io.*;

public class IOServiceImpl implements IOService {

  private final BufferedReader bufferedReader;
  private final PrintStream printStream;

  public IOServiceImpl(InputStream inputStream, PrintStream printStream) {
    this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    this.printStream = printStream;
  }

  @Override
  public void println(String line) {
    printStream.println(line);
  }

  @Override
  public String readLine() {
    String ln;
    try {
      ln = bufferedReader.readLine();
    } catch (IOException e) {
      throw new IOServiceException("Ошибка ввода-вывода.", e);
    }
    return ln;
  }
}
