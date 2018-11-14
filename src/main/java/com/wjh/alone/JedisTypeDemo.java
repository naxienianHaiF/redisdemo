package com.wjh.alone;

import redis.clients.jedis.Jedis;

/**
 * redis key type
 * if the key is not exists , return none
 */
public class JedisTypeDemo {

    private static Jedis jedis = null;

    public static void main(String[] args) {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";
        jedis = JedisUtiles.getJedis(host, port, auth);

        String ceshi = jedis.type("ceshi");
        System.out.println("the key of ceshi type is " + ceshi);

        System.out.println("the key of redis.learn.set type is " + jedis.type("redis.learn.set"));
        System.out.println("the key of mylist is " + jedis.type("mylist"));
        System.out.println("the key of redis.learn.hash is " + jedis.type("redis.learn.hash"));
        System.out.println("the key of redis.learn.zset is "+ jedis.type("redis.learn.zset"));

    }
}
