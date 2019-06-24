package fileparser;

import fileparser.Publisher.Message;

public class FilePublisher {
  private final Reader reader;
  private final Publisher publisher;

  public FilePublisher(Reader reader, Publisher publisher) {
    this.reader = reader;
    this.publisher = publisher;
  }

  public void parse() {
    reader
      .readFile()
      .forEach(line -> publisher.sendMessage(new Message(line)));
  }
}