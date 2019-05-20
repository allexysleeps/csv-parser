package FileParser.FileReceiver;

import FileParser.TransferQueue.TransferQueue;
import FileParser.TransferQueue.TransferQueueImpl;
import PubSub.Message;
import PubSub.Subscriber;

public class FileReceiver implements Subscriber {
  private final TransferQueue queue = new TransferQueueImpl();
  private final String name;
  private final int delay;
  private boolean started = false;
  private final Thread sendThread = new Thread(new Runnable() {
    @Override
    public void run() {
      Message msg = queue.getItem();
      while (msg != null) {
        try {
          Thread.sleep(delay);
          System.out.println(name + ": " + msg.body);
          msg = queue.getItem();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  });

  public FileReceiver(String name) {
    this.name = name;
    this.delay = 100;
  }
  public FileReceiver(String name, int delay) {
    this.name = name;
    this.delay = delay;
  }

  public void update (Message msg) {
    queue.addItem(msg);
    System.out.println("added to queue");
    if (!this.started) {
      this.started = true;
      sendThread.start();
    }
  }
}