import fileparser.Publisher;
import fileparser.Subscriber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublisherTest {
  class MockedSubscriber implements Subscriber {
    private final List<String> values = new ArrayList<>();

    List<String> getValues() {
      return values;
    }

    public void update(Publisher.Message message) {
      values.add(message.body);
    }
  }

  @Test
  @DisplayName("Single subscriber test")
  public void singleSubscriberTest() {
    List<String> testValues = new ArrayList<>(Arrays.asList("0", "1", "2", "3"));
    Publisher publisher = Publisher.create();
    MockedSubscriber subscriber = new MockedSubscriber();
    publisher.subscribe(subscriber);

    testValues.forEach(i -> publisher.sendMessage(new Publisher.Message(i)));
    assertEquals(testValues, subscriber.getValues());
  }

  @Test
  @DisplayName("Multi subscribers test")
  public void multiSubscribersTest() {
    List<String> testValues = new ArrayList<>(Arrays.asList("0", "1", "2", "3"));
    Publisher publisher = Publisher.create();
    MockedSubscriber subscriber1 = new MockedSubscriber();
    MockedSubscriber subscriber2 = new MockedSubscriber();
    MockedSubscriber subscriber3 = new MockedSubscriber();
    publisher.subscribe(subscriber1);
    publisher.subscribe(subscriber2);
    publisher.subscribe(subscriber3);

    testValues.forEach(i -> publisher.sendMessage(new Publisher.Message(i)));
    assertEquals(testValues, subscriber1.getValues());
    assertEquals(testValues, subscriber2.getValues());
    assertEquals(testValues, subscriber3.getValues());
  }

  @Test
  @DisplayName("Bulk subscription test")
  public void bulkSubscriptionTest() {
    List<String> testValues = new ArrayList<>(Arrays.asList("0", "1", "2", "3"));
    Publisher publisher = Publisher.create();
    MockedSubscriber subscriber1 = new MockedSubscriber();
    MockedSubscriber subscriber2 = new MockedSubscriber();
    MockedSubscriber subscriber3 = new MockedSubscriber();
    publisher.bulkSubscribe(Arrays.asList(
      subscriber1,
      subscriber2,
      subscriber3
    ));

    testValues.forEach(i -> publisher.sendMessage(new Publisher.Message(i)));
    assertEquals(testValues, subscriber1.getValues());
    assertEquals(testValues, subscriber2.getValues());
    assertEquals(testValues, subscriber3.getValues());
  }

  @Test
  @DisplayName("unsubscribe test")
  public void unsubscribeTest() {
    List<String> testValues = new ArrayList<>(Arrays.asList("0", "1", "2"));
    Publisher publisher = Publisher.create();
    MockedSubscriber subscriber1 = new MockedSubscriber();
    MockedSubscriber subscriber2 = new MockedSubscriber();
    publisher.subscribe(subscriber1);
    publisher.subscribe(subscriber2);

    testValues.forEach(i -> publisher.sendMessage(new Publisher.Message(i)));
    publisher.unsubscribe(subscriber2);
    publisher.sendMessage(new Publisher.Message("3"));

    assertEquals(testValues, subscriber2.getValues());
    assertEquals(Arrays.asList("0", "1", "2", "3"), subscriber1.getValues());
  }
}