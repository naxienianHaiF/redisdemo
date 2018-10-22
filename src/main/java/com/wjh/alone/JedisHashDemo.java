package com.wjh.alone;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JHW
 * @date created at 2018/10/22
 * Hash使用demo
 */
public class JedisHashDemo {

    private static Jedis jedis = null;

    public static void main(String[] args) {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";
        jedis = JedisUtiles.getJedis(host, port, auth);

        set();
        get();
    }

    /**
     * 如果hash中的值已经存在，则会修改其中的值
     */
    private static void set() {
        Long insert = jedis.hset("redis.learn.hash", "date", "20181022");
        System.out.println("总共插入了  "+insert+"  条数据");

        Map<String, String> map = new HashMap<>();
        for (int i=0;i<2;i++){
            map.put("key:"+i, "value:"+(i+i));
        }
        String hmset = jedis.hmset("redis.learn.hash", map);
        System.out.println("hmset return value is "+hmset);
        /**
        设置过期时间，单位秒
         */
        jedis.expire("redis.learn.hash",120);
    }

    private static void get(){
        Map<String, String> map = jedis.hgetAll("redis.learn.hash");
        System.out.println("==========>get");
        map.forEach((k,v) -> System.out.println("key="+k+" ,value="+v));

        List<String> list = jedis.hmget("redis.learn.hash", "key:1", "date");
        System.out.println("==========>hmget");
        list.forEach(e -> System.out.println("[value]"+e));

        List<String> hvals = jedis.hvals("redis.learn.hash");
        System.out.println("==========>hvals");
        hvals.forEach(v -> System.out.println(v));

        Long hlen = jedis.hlen("redis.learn.hash");
        System.out.println("hlen = "+hlen);
    }

}
