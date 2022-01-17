import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Movements {

  private static double income = 0.0;
  private static double expense = 0.0;

  public Movements(String pathMovementsCsv) {

    int i = 0;
    try {
      List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
      for (String line : lines) {
        if (i != 0) {
          String[] fragments = line.split("\\,");

          if (fragments.length == 8) {
            income += Double.parseDouble(fragments[6]);
            expense += Double.parseDouble(fragments[7]);
          } else if (fragments.length == 9) {
            if (fragments[6].startsWith("\"")) { // income = double
              String strIncome;
              strIncome = fragments[6].replace("\"", "") + "." + fragments[7].replace("\"", "");
              income += Double.parseDouble(strIncome);
              expense += Double.parseDouble(fragments[8]);
            } else if (fragments[7].startsWith("\"")) { // expense = double
              String strExpense;
              strExpense = fragments[7].replace("\"", "") + "." + fragments[8].replace("\"", "");
              income += Double.parseDouble(fragments[6]);
              expense += Double.parseDouble(strExpense);
            }
          }
        } else {
          i++;
        }
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public double getExpenseSum() {
    return expense;
  }

  public double getIncomeSum() {
    return income;
  }
}
