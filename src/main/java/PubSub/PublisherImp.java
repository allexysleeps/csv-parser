package PubSub;

import java.util.LinkedList;
import java.util.List;

public class PublisherImp implements Publisher {
  private List<Subscriber> subscribers = new LinkedList<>();

  public void subscribe(Subscriber sub) {
    subscribers.add(sub);
  }

  public void bulkSubscribe(List<Subscriber> subs) {
    this.subscribers.addAll(subs);
  }

  public void unsubscribe(Subscriber sub) {
    subscribers.remove(sub);
  }

  public void sendMessage(Message msg) {
    subscribers.forEach(sub -> sub.update(msg));
  }

}