package FileParser;

import FileParser.Transformer.Transformer;
import FileParser.Transformer.TransformerDropFields;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import FileParser.Reader.Reader;
import FileParser.Reader.ReaderCSV;

import java.util.Arrays;

@RestController
public class Controllers {
  @RequestMapping(method = RequestMethod.GET, value = "/csv")
  public HttpStatus getCSV() {
    Transformer transformer = new TransformerDropFields(Arrays.asList(0, 2));
    Reader reader = new ReaderCSV("./files/sample-small.csv", transformer);
    reader.readFile().forEach(System.out::println);
    return HttpStatus.OK;
  }
}