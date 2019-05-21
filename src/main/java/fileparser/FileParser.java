package fileparser;

import fileparser.filereceiver.FileReceiver;
import fileparser.reader.Reader;
import fileparser.reader.ReaderCSV;
import pubsub.Message;
import pubsub.Publisher;
import pubsub.PublisherImp;

import java.util.Arrays;
import java.util.stream.Stream;

public class FileParser {
  private final Reader reader;
  private final Publisher publisher;

  public FileParser(String path) {
    this.reader = new ReaderCSV(path);
    this.publisher = new PublisherImp();
    this.publisher.bulkSubscribe(Arrays.asList(
      new FileReceiver("Slow receiver", 1000),
      new FileReceiver("Fast receiver", 300)
    ));
  }

  public void parse() {
    reader
      .readFile()
      .orElse(Stream.empty())
      .forEach(line -> publisher.sendMessage(new Message(line)));
  }
}