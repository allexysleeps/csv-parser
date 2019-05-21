package fileparser.transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TransformerDropFields implements Transformer {
  private List<Integer> droppedFields;

  public TransformerDropFields(List<Integer> droppedFields) {
    this.droppedFields = droppedFields;
  }

  public String transformLine(String line) {
    List <String> oldLine = Arrays.asList(line.split(";"));
    List <String> newLine = new ArrayList<>();

    IntStream.range(0, oldLine.size()).forEach(idx -> {
      if (!droppedFields.contains(idx)) {
        newLine.add(oldLine.get(idx));
      }
    });

    return String.join(";", newLine);
  }
}