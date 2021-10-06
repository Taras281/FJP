public class Main {

  public static void main(String[] args) throws InterruptedException {
    Bank bank = new Bank();
    Account firstClient = new Account("1", 100);
    Account secondClient = new Account("2", 100);

    bank.addAccountToBank(firstClient);
    bank.addAccountToBank(secondClient);

    Thread threadOne =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                  bank.transfer(firstClient.getAccNumber(), secondClient.getAccNumber(), 100);
                    bank.transfer(secondClient.getAccNumber(), firstClient.getAccNumber(), 100);
                  bank.getBalance(firstClient.getAccNumber());
                  bank.getBalance(secondClient.getAccNumber());
                  System.out.println(Thread.currentThread().getName());
              }
            });

    Thread threadTwo =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                  bank.transfer(secondClient.getAccNumber(), firstClient.getAccNumber(), 100);
                  bank.transfer(firstClient.getAccNumber(), secondClient.getAccNumber(), 100);
                  bank.getBalance(firstClient.getAccNumber());
                  bank.getBalance(secondClient.getAccNumber());
                  System.out.println(Thread.currentThread().getName());
              }
            });

    threadOne.start();
    threadTwo.start();

    threadOne.join();
    threadTwo.join();
  }
}
