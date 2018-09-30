package com.wjh.sentinel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>sentinel known-slave mymaster 127.0.0.1 6380  这个配置
 * 导致Jedis连接报错 。错误信息如下【信息: Created JedisPool to master at 127.0.0.1:6381
 * redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool】</p>
 * <p>sentinel.conf修改ip为公网ip后就可以正常访问了</p>
 */
public class SentinelOne {

    public static void main(String[] args) {
        Set<String> sentinels = new HashSet<>();
        sentinels.add("39.105.93.97:26379");
        sentinels.add("39.105.93.97:26380");
        sentinels.add("39.105.93.97:26381");

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(10);

        JedisSentinelPool pool = new JedisSentinelPool("mymaster",sentinels, config);
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("naxienian1992");

            String result = jedis.set("sentinel","test redis sentinel");
            System.out.println("the result is "+ result);
            System.out.println(jedis.get("sentinel"));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
            if (pool != null){
                pool.close();
            }
        }
    }
}
