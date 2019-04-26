package FileParser;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controllers {
  @RequestMapping(method = RequestMethod.GET, value = "/csv")
  public void getCSV(final HttpServletRequest request, final HttpServletResponse response) {
    try {
      response.setContentType("application/csv");
      ServletOutputStream outStream = response.getOutputStream();
      List<String> lines = new CsvReader().readFile("./files/sample.csv");
      lines.forEach((l) -> {
        try {
          outStream.print(l);
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