package FileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

interface Reader {
  public List<String> readFile(String path);
}

class CsvReader implements Reader {
  public List<String> readFile (String path) {
    LinkedList<String> list = new LinkedList<>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(path));
      int k = 0;
      String line = reader.readLine();
      while (line != null) {
        list.add(line);
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }
}