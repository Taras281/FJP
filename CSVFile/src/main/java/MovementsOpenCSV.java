import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MovementsOpenCSV {

  private static double income = 0.0;
  private static double expense = 0.0;

  public MovementsOpenCSV(String pathMovementsCsv) throws CsvValidationException, IOException {
    Reader reader = Files.newBufferedReader(Paths.get(pathMovementsCsv));

    CSVParser parser =
        new CSVParserBuilder()
            .withSeparator(',')
            .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES)
            .build();

    CSVReader csvReader =
        new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();

    String[] fragments;

    while ((fragments = csvReader.readNext()) != null) {
      String str = fragments[6].replace(",", ".");
      String str2 = fragments[7].replace(",", ".");
      income += Double.parseDouble(str);
      expense += Double.parseDouble(str2);
    }
  }

  public double getIncome() {
    return income;
  }

  public double getExpense() {
    return expense;
  }
}
