import java.util.Scanner;

public class UserInput {
  public static String getLine() {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    return input;
  }
}
