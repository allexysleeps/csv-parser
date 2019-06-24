package fileparser;

import java.util.stream.Stream;

public interface Reader {
  Stream<String> readFile();

  enum ReaderType {
    CSV
  }
  static Reader create(ReaderType type, String path) {
    switch (type) {
      case CSV: return new ReaderCSV(path);
      default: throw new RuntimeException(String.format("type [{%s}] is not supported yet", type));
    }
  }
}