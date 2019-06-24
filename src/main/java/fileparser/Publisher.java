package fileparser;

import java.util.List;

public interface Publisher {
  void subscribe(Subscriber subscriber);
  void bulkSubscribe(List <Subscriber> subscribers);
  void unsubscribe(Subscriber subscriber);
  void sendMessage(Message message);

  class Message {
    public final String body;
    public Message(String body) {
      this.body = body;
    }
  }

  static Publisher create() {
    return new PublisherImp();
  }

}
