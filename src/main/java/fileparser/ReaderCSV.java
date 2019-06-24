package fileparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

public class ReaderCSV implements Reader {
  private final String filePath;

  ReaderCSV(String path) {
    this.filePath = path;
  }

  public Stream<String> readFile() {
    try(BufferedReader br = Files.newBufferedReader(Paths.get(this.filePath))) {
      return br.lines();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Couldn't read file " + this.filePath, e);
    }
  }
}