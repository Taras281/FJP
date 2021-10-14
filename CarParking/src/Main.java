public class Main {

  public static void main(String[] args) {
      Parking parking = new Parking();
    Thread threadOne = new Thread(new Producer(parking));
    Thread threadTwo = new Thread(new Consumer(parking));

    threadOne.start();
    threadTwo.start();
  }
}
