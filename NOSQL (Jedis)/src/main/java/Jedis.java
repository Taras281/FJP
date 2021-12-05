import java.util.Random;

public class Jedis {

  private static final int USERS = 20;
  private static int paidUser;

  private static void log(int user) {
    String log = "На главной странице показываем пользователя " + user;
    System.out.println(log);
  }

  public static void main(String[] args) throws InterruptedException {
    JedisStorage jedis = new JedisStorage();
    jedis.init();

    while (true) {
      for (int i = 1; i <= USERS; i++) {
        if (i % 10 == 0) {
          paidUser = new Random().nextInt(20);
          Thread.sleep(500);
          System.out.println("Пользователь " + paidUser + " оплатил платную услугу");
          jedis.addUser(paidUser);
          log(paidUser);
        }
        if (paidUser == i) {
          continue;
        }
        jedis.addUser(i);
        log(i);
        Thread.sleep(1000);
      }
      jedis.deleteAll();
    }
  }
}
