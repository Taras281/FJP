import redis.clients.jedis.Jedis;

import java.util.Date;

public class RedisStorage {

    private static Jedis jedis;
    private static final String KEY = "UsersQueue";

    public void init() {
        jedis = new Jedis("localhost", 6379);
    }

    public void deleteAll() {
        jedis.flushDB();
    }

    public void addUser(int user) {
        jedis.zadd(KEY, getTs(), String.valueOf(user));
    }

    public void showQueue() {
        System.out.println(jedis.zrange(KEY, 0, -1));
    }

    private double getTs() {
        return new Date().getTime() / 1000;
    }
}
