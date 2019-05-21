package fileparser.reader;

import java.util.Optional;
import java.util.stream.Stream;

public interface Reader {
  Optional <Stream<String>> readFile();
}