package com.wjh.alone;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class ZsetDemo {

    private static Jedis jedis = null;

    public static void main(String[] args) {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";

        jedis = JedisUtiles.getJedis(host, port, auth);
    }

    private static void zadd() {
        jedis.zadd("redis.learn.zset", 12.0,"shi er fen.");
        Map<String, Double> map = new HashMap<>();
        map.put("shi san fen.", 13.0);

        jedis.zadd("redis.learn.zset", map);

        Long zcard = jedis.zcard("redis.learn.zset");


    }
}
