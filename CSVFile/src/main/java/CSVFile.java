
/**
 * Автор: https://github.com/VlasovM
 * В данной задаче поработаем с CSV файлами. Класс Movements -> обычные регулярные выражения,
 * класс -//-OpenCSV через библиотеку.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-rabota-s-csv-failami-ch1
 * https://vk.com/@javlasov-rabota-s-csv-failami-ch2
 *
 */

public class CSVFile {

  public static void main(String[] args) {

    Movements movements = new Movements(MovementList.List.getPATH());
    System.out.println(movements.getIncomeSum());

    try {
      MovementsOpenCSV openCSV = new MovementsOpenCSV(MovementList.ListFloat.getPATH());
      System.out.println(openCSV.getExpense());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
