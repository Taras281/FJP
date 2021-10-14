public class Parking {

  private final int parkingSize = 500;
  private int carCount;

  public synchronized void in() {
    if (carCount == parkingSize) {
      try {
      wait();
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
    carCount++;
    System.out.println("Автомобиль завезён");
    System.out.println("Свободных мест: " + (parkingSize - carCount));
    notify();
  }



  public synchronized void  out() {
    if (carCount == 0) {
      try {
        wait();
        } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
    carCount--;
    System.out.println("Автомобиль вывезен");
    System.out.println("Свободных мест: " + (parkingSize - carCount));
    notify();
  }
}
