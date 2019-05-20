package FileParser;

import FileParser.FileReceiver.FileReceiver;
import FileParser.Reader.Reader;
import FileParser.Reader.ReaderCSV;
import PubSub.Message;
import PubSub.Publisher;
import PubSub.PublisherImp;

import java.util.Arrays;

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
        .forEach(line -> publisher.sendMessage(new Message(line)));
  }
}