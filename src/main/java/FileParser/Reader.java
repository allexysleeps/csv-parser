package FileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

interface Transformer {
  String transformLine(String line);
}

interface Reader {
  List<String> readFile();
  void setTransformer(Transformer transform);
}

class CsvReader implements Reader {
  private class IdentityTransformer implements Transformer {
    public String transformLine(String line) {
      return line;
    }
  }

  private String filePath;
  private Transformer transformer = new IdentityTransformer();

  public CsvReader(String path) {
    this.filePath = path;
  }

  public void setTransformer(Transformer transformer) {
    this.transformer = transformer;
  }

  public List<String> readFile () {
    LinkedList<String> list = new LinkedList<>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(this.filePath));
      String line = reader.readLine();
      while (line != null) {
        list.add(this.transformer.transformLine(line));
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }
}