package Application;

import fileparser.FilePublisher;
import fileparser.FileReceiver;
import fileparser.Publisher;
import fileparser.Reader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
public class Controllers {
  @RequestMapping(method = RequestMethod.GET, value = "/csv")
  public HttpStatus getCSV() {
    Publisher publisher = Publisher.create();
    publisher.bulkSubscribe(
      Arrays.asList(
        new FileReceiver("Fast receiver", 100),
        new FileReceiver("Slow receiver", 5000)
    ));

    new FilePublisher(
      Reader.create(Reader.ReaderType.CSV, "./files/sample-tiny.csv"),
      publisher
    ).parse();

    return HttpStatus.OK;
  }
}