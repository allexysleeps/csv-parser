package fileparser;

public interface Subscriber {
  void update(Publisher.Message message);
}