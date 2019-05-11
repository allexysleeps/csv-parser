package Application;

import FileParser.FileParser;
import FileParser.Transformer.Transformer;
import FileParser.Transformer.TransformerDropFields;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class Controllers {
  @RequestMapping(method = RequestMethod.GET, value = "/csv")
  public HttpStatus getCSV() {
    FileParser fileParser = new FileParser("./files/sample-tiny.csv");
    fileParser.parse();
    return HttpStatus.OK;
  }
  @RequestMapping(method = RequestMethod.GET, value = "/csv-filter")
  public HttpStatus filterCSV() {
    Transformer transformer = new TransformerDropFields(Arrays.asList(0, 2));
    FileParser fileParser = new FileParser("./files/sample-tiny.csv", transformer);
    fileParser.parse();
    return HttpStatus.OK;
  }
}