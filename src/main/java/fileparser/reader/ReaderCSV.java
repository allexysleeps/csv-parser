package fileparser.reader;

import fileparser.transformer.Transformer;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class ReaderCSV implements Reader {
  private class IdentityTransformer implements Transformer {
    public String transformLine(String line) {
      return line;
    }
  }

  private final String filePath;
  private final Transformer transformer;

  public ReaderCSV(String path) {
    this.filePath = path;
    this.transformer = new IdentityTransformer();
  }

  public ReaderCSV(String path, Transformer transformer) {
    this.filePath = path;
    this.transformer = transformer;
  }

  public Optional<Stream<String>> readFile() {
    try {
      BufferedReader br = Files.newBufferedReader(Paths.get(this.filePath));
      return Optional.of(br.lines().map(this.transformer::transformLine));
    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }
}