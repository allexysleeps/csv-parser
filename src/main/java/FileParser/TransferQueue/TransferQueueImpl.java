package FileParser.TransferQueue;

import java.util.LinkedList;
import java.util.Queue;

import PubSub.Message;

public class TransferQueueImpl implements TransferQueue {
  private Queue <Message> queue = new LinkedList<>();

  public void addItem(Message item) {
    queue.add(item);
  }

  public Message getItem() {
    return queue.poll();
  }
}