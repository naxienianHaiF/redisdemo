package com.wjh.alone;

import redis.clients.jedis.Jedis;

/**
 * @author wjh
 * 通过Jedis连接
 */
public class JedisConnectionDemo {

    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = new Jedis("39.105.93.97", 6378);
            jedis.auth("naxienian1992");
            String result = jedis.set("jedisalone", "standalone");
            System.out.println("the result value is "+result);
            System.out.println(jedis.get("jedisalone"));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
