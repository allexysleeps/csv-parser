package FileParser.Reader;

import java.util.stream.Stream;

public interface Reader {
  Stream<String> readFile();
}