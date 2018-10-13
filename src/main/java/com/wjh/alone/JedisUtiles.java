package com.wjh.alone;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtiles {
    private static JedisPoolConfig config = null;
    private static JedisPool pool = null;
    private static Jedis jedis = null;

    private JedisUtiles() {}

    /**
     * get jedis
     * @param host ip address
     * @param port port
     * @param auth redis password
     * @return
     */
    public static Jedis getJedis(String host, int port, String auth) {
        if (pool == null) {
            init();
            pool = new JedisPool(config, host, port);
            jedis = pool.getResource();
        }
        if (auth != null){
            jedis.auth(auth);
        }
        return jedis;
    }

    private static void init() {
        config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(10);

    }
}
