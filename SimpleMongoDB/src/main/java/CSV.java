import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSV {

  private static List<String[]> linesCSV;

  public List<String[]> getListCSV(String path) {
    try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
      CSVParser parser =
          new CSVParserBuilder()
              .withSeparator(',')
              .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES)
              .build();

      CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();

      linesCSV = csvReader.readAll();
    } catch (java.io.IOException IOException) {
      IOException.printStackTrace();
    }
    return linesCSV;
  }
}
