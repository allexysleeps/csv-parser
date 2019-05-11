package FileParser;

import FileParser.FileReceiver.FileReceiver;
import FileParser.Reader.Reader;
import FileParser.Reader.ReaderCSV;
import FileParser.Transformer.Transformer;
import PubSub.Message;
import PubSub.Publisher;
import PubSub.PublisherImp;

public class FileParser {
  private final Reader reader;
  private final Publisher publisher;

  public FileParser(String path) {
    this.reader = new ReaderCSV(path);
    FileReceiver receiver = new FileReceiver();
    this.publisher = new PublisherImp();
    this.publisher.subscribe(receiver);
  }

  public FileParser(String path, Transformer transformer) {
    this.reader = new ReaderCSV(path, transformer);
    this.publisher = new PublisherImp();
    FileReceiver receiver = new FileReceiver();
    this.publisher.subscribe(receiver);
  }

  public void parse() {
    reader
        .readFile()
        .forEach(line -> publisher.sendMessage(new Message(line)));
  }
}