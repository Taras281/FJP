public class Main {

    private static final int TIME_TO_WAIT = 100; // milliseconds

  public static void main(String[] args) {
    MouseMoving mouseMoving = new MouseMoving(TIME_TO_WAIT);
    Thread thread = new Thread(mouseMoving);
    thread.start();
  }
}
