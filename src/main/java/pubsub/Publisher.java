package pubsub;

import java.util.List;

public interface Publisher {
  void subscribe(Subscriber subscriber);
  void bulkSubscribe(List <Subscriber> subscribers);
  void unsubscribe(Subscriber subscriber);
  void sendMessage(Message message);
}
