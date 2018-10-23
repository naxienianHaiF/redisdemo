package com.wjh.alone;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author wjh
 * redis set集合操作
 */
public class JedisSetDemo {
    private static Jedis jedis = null;

    public static void main(String[] args) {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";
        jedis = JedisUtiles.getJedis(host, port, auth);

        set();
        get();
        sdiff();
    }

    private static void set(){
        Long sadd = jedis.sadd("redis.learn.set", "s1", "s2");
        System.out.println("sadd插入了 "+ sadd +" 条数据");

    }

    private static void get() {
        Long len = jedis.scard("redis.learn.set");
        System.out.println("总共有 " + len +" 个元素");

        /**
         * 获取集合中所有的元素
         */
        Set<String> smembers = jedis.smembers("redis.learn.set");
        System.out.println("===========>");
        smembers.forEach(e -> System.out.println(e));
        System.out.println("<===========");
    }

    /**
     * Redis Sdiff 命令返回给定集合之间的差集。不存在的集合 key 将视为空集。
     * 差集的结果来自前面的 FIRST_KEY ,而不是后面的 OTHER_KEY1，也不是整个 FIRST_KEY OTHER_KEY1..OTHER_KEYN 的差集。
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * SDIFF key1 key2 key3 = {b,d}
     */
    private static void sdiff() {
        jedis.sadd("redis.learn.sdiff1", "a", "b" ,"c", "d");
        jedis.sadd("redis.learn.sdiff2", "c");
        jedis.sadd("redis.learn.sdiff3", "a","c","e");

        Set<String> sdiff = jedis.sdiff("redis.learn.sdiff1",
                "redis.learn.sdiff2", "redis.learn.sdiff3");
        sdiff.forEach(e -> System.out.println(e));

//        返回给定所有集合的差集并存储在 destination 中
        Long sdiffstore = jedis.sdiffstore("redis.learn.sdiff3", "redis.learn.sdiff1", "redis.learn.sdiff2");
        System.out.println("sdiffstore = "+sdiffstore +"\r\n================");

        jedis.smembers("redis.learn.sdiff3").forEach(e -> System.out.println(e));

        System.out.println("=========交集sinter======");
        Set<String> sinter = jedis.sinter("redis.learn.sdiff1", "redis.learn.sdiff2");
        sinter.forEach(e -> System.out.println(e));
    }
}
