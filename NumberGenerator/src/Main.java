import java.util.HashSet;
import java.util.Set;

public class Main {

    final static int TOTAL_NUMBER = 10;

    public static void main(String[] args) {

        Number number = new Number();

        for (int i = 0; i < TOTAL_NUMBER; i++) {
            System.out.println(number.generateNewNumber());
        }
    }
}
