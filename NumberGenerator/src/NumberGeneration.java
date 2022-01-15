import java.util.HashSet;
import java.util.Set;

/**
 * Автор: https://github.com/VlasovM
 * В данной задаче познакомимся с классом Math.Random(). Сделаем генерацию номеров России.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-znakomstvo-i-rabota-s-mathrandom
 */

public class NumberGeneration {

    final static int TOTAL_NUMBER = 10;

    public static void main(String[] args) {

        Number number = new Number();

        for (int i = 0; i < TOTAL_NUMBER; i++) {
            System.out.println(number.generateNewNumber());
        }
    }
}
