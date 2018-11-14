package com.wjh;

import com.wjh.alone.JedisUtiles;
import com.wjh.alone.lock.RedisLock;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisLockTest {

    private static Jedis jedis = null;

    @Before
    public void before() {
        System.out.println("[before]");
    }

    @BeforeClass
    public static void init() {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";
        jedis = JedisUtiles.getJedis(host, port, auth);
        System.out.println("[beforeclass]");
    }

    @Test
    public void tryGetDistributedLock() {
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid : "+ uuid);
        boolean result = RedisLock.tryGetDistributedLock(jedis, "redis.learn.lock", uuid, 120000);
        System.out.println("the method tryGetDistributedLock reulst is "+result);
    }

    @Test
    public void releaseDistributedLock () {
        String value = jedis.get("redis.learn.lock");
        System.out.println("the key redis.learn.lock value is "+ value);
        System.out.println("value is not redis key value : "+ RedisLock.releaseDistributedLock(jedis,
                "redis.learn.lock", value+1));
        boolean result = RedisLock.releaseDistributedLock(jedis, "redis.learn.lock", value);
        System.out.println("value is redis key : "+ result);
    }

    /**
     * beforeclass所有的测试方法只执行一次，before是都执行
     * <p>如果每次只执行一个方法，则都是执行一次；如果是执行整个文件，则beforeclass只执行一次</p>
     * <p>afterclass和after同理</p>
     */
    @Test
    public void beforeClass() {
        System.out.println("test beforeclass and before...");
    }

    @AfterClass
    public static void destory() {
        if (jedis != null) {
            jedis.close();
        }
        System.out.println("[afterclass]");
    }
}
