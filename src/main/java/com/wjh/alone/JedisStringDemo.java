package com.wjh.alone;

import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisStringDemo {

    public static void main(String[] args) {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";

        Jedis jedis = JedisUtiles.getJedis(host, port, auth);

        String result = jedis.set("ceshi", "test redis");
        System.out.println("jedis.set method reuslt is "+ result);
        System.out.println("jedis.get : key is ceshi ,value is "+ jedis.get("ceshi") );
        /**
        设置过期时间，单位：秒
         */
        jedis.setex("expiretime",100, "ceshi redis guoqi shijian");
        /**
         * 设置国企时间，单位：毫秒
         */
        jedis.psetex("haomiao", 1000, "ceshi redis haomiao");

        /**
         * 将 key 的值设为 value ，当且仅当 key 不存在。
         * 若给定的 key 已经存在，则 SETNX 不做任何动作。
         * SETNX 是『SET if Not exists』(如果不存在，则 SET)的简写。
         * 如果存在返回 0，不存在返回 1
         */
        Long nx = jedis.setnx("setnx", "redis method : setnx");
        Long ex = jedis.setnx("setnx", "the key is exists");
        System.out.println(String.format("[setnx] the key is not exists ,the return value is %s,\r\n" +
                "the key is exists, the return value is  %s", nx, ex));

        /**
         * 设置多个key value，或者多个key
         */
        String msets = jedis.mset("mset1","mvalue1","mset2","mvalue2");
        System.out.println("==========>"+ msets);
        List<String> mget = jedis.mget("mset1", "mset2", "setnx");
        mget.forEach(e -> System.out.println(e));

    }
}
