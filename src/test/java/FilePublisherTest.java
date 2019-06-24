import fileparser.FilePublisher;
import fileparser.Publisher;
import fileparser.Reader;
import fileparser.Subscriber;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePublisherTest {
  class MockedReader implements Reader {
    private final Stream<String> mockedLines;

    MockedReader(List<String> list) {
      this.mockedLines = list.stream();
    }

    public Stream<String> readFile() {
      return mockedLines;
    }
  }

  class MockedPublisher implements Publisher {
    final List<String> messages = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {}
    public void bulkSubscribe(List<Subscriber> subscribers) {}
    public void unsubscribe(Subscriber subscriber) {}

    public void sendMessage(Message message) {
      messages.add(message.body);
    }
  }

  @Test
  @DisplayName("FilePublisher Test")
  public void filePublisherTest() {
    List <String> testLines = Arrays.asList("0", "1", "2", "3", "4");
    Reader reader = new MockedReader(testLines);
    MockedPublisher publisher = new MockedPublisher();
    new FilePublisher(reader, publisher).parse();
    assertEquals(testLines, publisher.messages);
  }
}