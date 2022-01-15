
/**
 * Автор: https://github.com/VlasovM
 * В данной задачке познакомимся с многопоточностью. Наиболее часто встречающаяся задачка именно на банковские
 * операции. Плюс вспомним, что существуют UNIT тесты для проверки работоспособности.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-mnogopotochnost-synchronized-unit-v-dogonku
 */

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Bank bank = new Bank();
    Account firstClient = new Account("1", 100);
    Account secondClient = new Account("2", 100);

    bank.addAccountToBank(firstClient);
    bank.addAccountToBank(secondClient);

    Thread threadOne =
        new Thread(
                () -> {
                  bank.transfer(firstClient.getAccNumber(), secondClient.getAccNumber(), 100);
                  bank.transfer(secondClient.getAccNumber(), firstClient.getAccNumber(), 100);
                  System.out.println(Thread.currentThread().getName());
                });

    Thread threadTwo =
        new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.transfer(secondClient.getAccNumber(), firstClient.getAccNumber(), 100);
                    bank.transfer(firstClient.getAccNumber(), secondClient.getAccNumber(), 100);
                    System.out.println(Thread.currentThread().getName());
                });

    threadOne.start();
    threadTwo.start();

    threadOne.join();
    threadTwo.join();
  }
}
