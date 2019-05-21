import pubsub.Message;
import pubsub.Publisher;
import pubsub.PublisherImp;
import pubsub.Subscriber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublisherTest {
  private final List<Message> mockMessages = Arrays.asList(
    new Message("message1"),
    new Message("message2"),
    new Message("message3")
  );

  @Test
  @DisplayName("PubSub single sub test")
  public void publisherTestSingleSub() {
    Publisher publisher = new PublisherImp();
    final List<Message> resultList = new LinkedList<>();
    Subscriber mockSubscriber = resultList::add;

    publisher.subscribe(mockSubscriber);
    mockMessages.forEach(publisher::sendMessage);

    assertEquals(resultList, mockMessages);
  }

  @Test
  @DisplayName("PubSub multi subs test")
  public void publisherTestMultiSub() {
    Publisher publisher = new PublisherImp();
    final List<Message> resultList1 = new LinkedList<>();
    final List<Message> resultList2 = new LinkedList<>();

    Subscriber subs1 = resultList1::add;
    Subscriber subs2 = resultList2::add;

    publisher.bulkSubscribe(Arrays.asList(subs1, subs2));
    mockMessages.forEach(publisher::sendMessage);

    assertEquals(resultList1, mockMessages);
    assertEquals(resultList2, mockMessages);
  }

  @Test
  @DisplayName("PubSub unsubscription test")
  public void publisherTestUnsubscription() {
    Publisher publisher = new PublisherImp();
    final List<Message> resultList1 = new LinkedList<>();
    final List<Message> resultList2 = new LinkedList<>();

    Subscriber subs1 = resultList1::add;
    Subscriber subs2 = resultList2::add;

    publisher.bulkSubscribe(Arrays.asList(subs1, subs2));

    publisher.sendMessage(mockMessages.get(0));
    publisher.sendMessage(mockMessages.get(1));
    publisher.unsubscribe(subs1);
    publisher.sendMessage(mockMessages.get(2));

    assertEquals(resultList2, mockMessages);
    assertEquals(
      resultList1,
      Arrays.asList(
        mockMessages.get(0),
        mockMessages.get(1)
      )
    );
  }
}