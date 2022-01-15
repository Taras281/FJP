/**
 * Автор: https://github.com/VlasovM
 * В данной задаче познакомимся с методами wait и notify, которые помогают работать приложению
 * более корректно в режиме многопоточности.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-metody-wait-i-notify
 */

public class CarParking {

  public static void main(String[] args) {
      Parking parking = new Parking();
    Thread threadOne = new Thread(new Producer(parking));
    Thread threadTwo = new Thread(new Consumer(parking));

    threadOne.start();
    threadTwo.start();
  }
}
