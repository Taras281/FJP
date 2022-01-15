
/**
 * Автор: https://github.com/VlasovM
 * В данной задаче просто посмотрим, как можно двигать мышкой при помощи класса Robot.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-programmka-kotoraya-dvigaet-myshku-klass-java-robot
 */

public class MouseMoving {

    private static final int TIME_TO_WAIT = 100; // milliseconds

  public static void main(String[] args) {
    MouseMovement mouseMoving = new MouseMovement(TIME_TO_WAIT);
    Thread thread = new Thread(mouseMoving);
    thread.start();
  }
}
