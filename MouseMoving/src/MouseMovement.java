import java.awt.*;

public class MouseMovement implements Runnable {

  private Robot robot;
  private long timeToWait;
  private int coordinateX;
  private int coordinateY;

  public MouseMovement(long timeToWait) {
    this.timeToWait = timeToWait;

    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    coordinateX = 600;
    coordinateY = 200;

    while (true) {

      if (coordinateX < 1200 & coordinateY == 200) {
        coordinateX += 20;
      }
      if (coordinateX == 1200 & coordinateY < 400) {
        coordinateY += 20;
      }
      if (coordinateX > 600 & coordinateY == 400) {
        coordinateX -= 20;
      }
      if (coordinateX == 600 & coordinateY > 200) {
        coordinateY -= 20;
      }
      robot.mouseMove(coordinateX, coordinateY);

      try {
        Thread.sleep(timeToWait);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
