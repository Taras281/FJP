import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MovementsOpenCSV {

  static double income = 0.0;
  static double expense = 0.0;

  public MovementsOpenCSV(String pathMovementsCsv) {
    try {

      Reader reader = Files.newBufferedReader(Paths.get(pathMovementsCsv));

      CSVParser parser = new CSVParserBuilder()
          .withSeparator(',')
          .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES)
          .build();

      CSVReader csvReader = new CSVReaderBuilder(reader)
          .withSkipLines(1)
          .withCSVParser(parser)
          .build();

      String[] fragments;

      while ((fragments = csvReader.readNext()) != null) {
        String str = fragments[6].replace(",", ".");
        String str2 = fragments[7].replace(",", ".");
        income += Double.parseDouble(str);
        expense += Double.parseDouble(str2);
      }


    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
