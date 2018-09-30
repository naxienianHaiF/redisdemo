package com.wjh.alone;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wjh created on 2018-09-30
 * 使用静态代码块
 */
public class RedisAloneStaticDemo {

    private static JedisPool pool = null;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(10);
        config.setMaxWaitMillis(100000);
        pool = new JedisPool(config,"39.105.93.97",6378);
    }

    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("naxienian1992");
            System.out.println(jedis.get("jedisalone"));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
            if (pool != null) {
                pool.close();
            }
        }
    }
}
