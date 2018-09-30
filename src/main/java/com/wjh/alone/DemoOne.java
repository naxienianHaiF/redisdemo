package com.wjh.alone;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 通过Redis连接池连接
 * 单机连接redis
 */
public class DemoOne {

    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        poolConfig.setBlockWhenExhausted(true);

        //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        poolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        //是否启用pool的jmx管理功能, 默认true
        poolConfig.setJmxEnabled(true);

        //最大空闲连接数, 默认8个
        poolConfig.setMaxIdle(8);
        //最大连接数, 默认8个
        poolConfig.setMaxTotal(10);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        poolConfig.setMaxWaitMillis(30000);

        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        poolConfig.setMinEvictableIdleTimeMillis(1800000);
        //最小空闲连接数, 默认0
        poolConfig.setMinIdle(0);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        poolConfig.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        poolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
        //在获取连接的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(false);
        //在空闲时检查有效性, 默认false
        poolConfig.setTestWhileIdle(false);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        poolConfig.setTimeBetweenEvictionRunsMillis(-1);

        JedisPool pool = new JedisPool(poolConfig,"39.105.93.97",6378, 30000, "naxienian1992");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String result = jedis.set("ceshi", "ceshi");
            System.out.println("result is " + result);
            System.out.println("========>"+ jedis.get("ceshi"));

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }
        System.out.println("========>redis pool 方法二");
        method(poolConfig);
    }

    private static void method(JedisPoolConfig config){
        JedisPool pool = new JedisPool(config, "39.105.93.97",6378);

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("naxienian1992");

            String result = jedis.set("auth", "naxienian1992");
            System.out.println("result is " + result);
            System.out.println("========>"+ jedis.get("auth"));

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //关闭jedis
            if (jedis != null){
                jedis.close();
            }
            //关闭连接池
            if (pool != null){
                pool.close();
            }
        }
    }
}
