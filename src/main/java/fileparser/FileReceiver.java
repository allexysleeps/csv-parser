package fileparser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import fileparser.Publisher.Message;

public class FileReceiver implements Subscriber {
  private final Queue <Publisher.Message> queue = new LinkedList<>();
  private final String name;
  private final int delay;
  private AtomicBoolean started = new AtomicBoolean(false);

  private void printData(Message msg) {
    System.out.println(name + ": " + msg.body);
  }

  private void sendData() {
    new Thread(() -> {
      Message msg = queue.poll();
      while (msg != null) {
        try {
          Thread.sleep(delay);
          printData(msg);
          msg = queue.poll();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public FileReceiver(String name, int delay) {
    this.name = name;
    this.delay = delay;
  }

  public void update (Message msg) {
    queue.add(msg);
    if (started.compareAndSet(false, true)) {
      sendData();
    }
  }
}