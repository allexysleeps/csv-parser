package PubSub;

public interface Publisher {
  void subscribe(Subscriber subscriber);
  void unsubscribe(Subscriber subscriber);
  void sendMessage(Message message);
}
