import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;

import FileParser.TransferQueue.TransferQueue;
import FileParser.TransferQueue.TransferQueueImpl;

public class TransferQueueTest {
  private final TransferQueue transferQueue = new TransferQueueImpl();
  @Test
  @DisplayName("TransferQueue test")
  public void queueTest() {
    transferQueue.addItem("first");
    transferQueue.addItem("second");
    assertEquals("first", transferQueue.getItem());
    assertEquals("second", transferQueue.getItem());
    assertNull(transferQueue.getItem());
    transferQueue.addItem("third");
    assertEquals("third", transferQueue.getItem());
    assertNull(transferQueue.getItem());
    assertNull(transferQueue.getItem());
  }
}