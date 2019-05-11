package FileParser.FileReceiver;

import PubSub.Message;
import PubSub.Subscriber;

public class FileReceiver implements Subscriber {
  public void update (Message msg) {
    try {
      Thread.sleep(300);
      System.out.println("Sent data");
      System.out.println(msg.body);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}