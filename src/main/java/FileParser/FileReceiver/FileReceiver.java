package FileParser.FileReceiver;

import PubSub.Message;
import PubSub.Subscriber;

import java.util.LinkedList;
import java.util.Queue;

public class FileReceiver implements Subscriber {
  private final Queue <Message> queue = new LinkedList<>();
  private final String name;
  private final int delay;
  private boolean started = false;
  private final Thread sendThread = new Thread(new Runnable() {
    @Override
    public void run() {
      Message msg = queue.poll();
      while (msg != null) {
        try {
          Thread.sleep(delay);
          System.out.println(name + ": " + msg.body);
          msg = queue.poll();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  });

  public FileReceiver(String name, int delay) {
    this.name = name;
    this.delay = delay;
  }

  public void update (Message msg) {
    queue.add(msg);
    if (!this.started) {
      this.started = true;
      sendThread.start();
    }
  }
}