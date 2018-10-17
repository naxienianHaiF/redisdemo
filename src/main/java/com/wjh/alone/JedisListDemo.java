package com.wjh.alone;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisListDemo {

    private static Jedis jedis = null;

    public static void main(String[] args) {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";
        jedis = JedisUtiles.getJedis(host, port, auth);

        test();
//        push();
    }

    /**
     * list列表左右增加数据
     */
    private static void push() {
        Long nums = jedis.lpush("mylist", "a", "c", "d");
        System.out.println("list列表中插入了"+nums+" 条数据");

        String lpop = jedis.lpop("mylist");
        System.out.println("lpop value is "+ lpop);

        /**
         * list 长度
         */
        Long len = jedis.llen("mylist");
        System.out.println("list len is "+ len);

        jedis.rpush("mylist", "e","f");
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        /**
         * 并行计算
         */
        mylist.stream().parallel().forEach(e -> System.out.println(e));
        System.out.println("=======并行计算========");
        mylist.forEach(e -> System.out.println(e));
    }

    private static void test() {
        /**
         * Redis Linsert 命令用于在列表的元素前或者后插入元素。当指定元素不存在于列表中时，不执行任何操作。
         * 当列表不存在时，被视为空列表，不执行任何操作。
         * 如果 key 不是列表类型，返回一个错误。
         */
        Long linsert = jedis.linsert("mylist", BinaryClient.LIST_POSITION.AFTER, "c", "a");
        jedis.linsert("mylist", BinaryClient.LIST_POSITION.AFTER, "c", "a");
        System.out.println("redis linsert : "+ linsert);

        List<String> mylist = jedis.lrange("mylist", 0, -1);
        mylist.forEach(e -> System.out.println(e));
        System.out.println("=====================>");

        String lindex = jedis.lindex("mylist", 1);
        System.out.println("jedis lindex: " + lindex);

        /**
         * count 为正整数的时候，表示从head到tail移除的个数
         * count 为负整数的时候，表示从tail到head移除的个数
         * count 等于0的时候，表示移除所所有的值为value的元素
         */
        Long lrem = jedis.lrem("mylist", 0, "a");
        System.out.println("<=====================");
        mylist = jedis.lrange("mylist", 0, -1);
        mylist.forEach(e -> System.out.println(e));
        System.out.println("=====================>");

        System.out.println("jedis lrem : "+ lrem);

    }
}
