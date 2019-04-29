package FileParser;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controllers {
  @RequestMapping(method = RequestMethod.GET, value = "/csv")
  public void getCSV(final HttpServletResponse response) {
    try {
      response.setContentType("application/csv");
      ServletOutputStream outStream = response.getOutputStream();

      TransformerFilterFields filterCols = new TransformerFilterFields(Arrays.asList(1, 2));

      Reader reader = new CsvReader("./files/sample-small.csv");
      reader.setTransformer(filterCols);

      List <String> lines = reader.readFile();

      lines.forEach((l) -> {
        try {
          outStream.print(l + "\n");
        } catch (IOException err) {
          err.printStackTrace();
        }
      });
      outStream.close();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }
}