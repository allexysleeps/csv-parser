package FileParser.TransferQueue;

import java.util.LinkedList;
import java.util.Queue;

public class TransferQueueImpl implements TransferQueue {
  private Queue <String> queue = new LinkedList<>();

  public void addItem(String item) {
    queue.add(item);
  }

  public String getItem() {
    return queue.poll();
  }
}