import java.util.Random;

/**
 * Автор: https://github.com/VlasovM
 * В данной задаче знакомимся с Jedis API - библиотекой, что позволит нам работать
 * с нереляционной базой данных Redis.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-nerelyacionnye-bazy-dannyh-znakomstvo-s-redis-i-s-jedis-api
 */

public class Redis {

    private static final int USERS = 20;
    private static int paidUser;

    private static void log(int user) {
        String log = "На главной странице показываем пользователя " + user;
        System.out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {
        RedisStorage jedis = new RedisStorage();
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
