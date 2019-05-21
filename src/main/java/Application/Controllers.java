package Application;

import fileparser.FileParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controllers {
  @RequestMapping(method = RequestMethod.GET, value = "/csv")
  public HttpStatus getCSV() {
    FileParser fileParser = new FileParser("./files/sample-tiny.csv");
    fileParser.parse();
    return HttpStatus.OK;
  }
}