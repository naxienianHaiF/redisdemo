package com.wjh.protocol;

import redis.clients.jedis.Jedis;

/**
 * @see SocketDemo
 * 测试redis的resp协议，和{@link SocketDemo}一起使用
 */
public class LocalSetDemo {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6378;
        Jedis jedis = new Jedis(host, port);
        jedis.set("redis.protocol.test",  "test");
        jedis.close();
    }
}
