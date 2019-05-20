import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import FileParser.Transformer.Transformer;
import FileParser.Transformer.TransformerDropFields;

import java.util.Arrays;
import java.util.Collections;

public class TransformerDropFieldsTest {
  @Test
  @DisplayName("TransformerDropFields test")
  public void transformTest() {
    Transformer transformer = new TransformerDropFields(Collections.singletonList(0));
    assertEquals("1;2;3", transformer.transformLine("0;1;2;3"));
    assertEquals("1;2;3", transformer.transformLine("0;1;2;3"));
    transformer = new TransformerDropFields(Arrays.asList(0, 3));
    assertEquals("1;2", transformer.transformLine("0;1;2;3"));
    assertEquals("1;2", transformer.transformLine("0;1;2"));
  }
}