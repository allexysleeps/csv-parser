package pubsub;

public interface Subscriber {
  void update(Message message);
}