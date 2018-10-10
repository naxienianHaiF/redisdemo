package com.wjh.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Redis Cluster在配置的时候需要用公网IP。使用127.0.0.1的
 * 时候，集群连接会报错。错误信息如下
 * 【redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool】
 * </p>
 */
public class ClusterOne {

    private static Set<HostAndPort> nodes = null;

    static {
        nodes = new HashSet<>();
        nodes.add(new HostAndPort("39.105.93.97",7000));
        nodes.add(new HostAndPort("39.105.93.97",7001));
        nodes.add(new HostAndPort("39.105.93.97",7002));
        nodes.add(new HostAndPort("39.105.93.97",7003));
        nodes.add(new HostAndPort("39.105.93.97",7004));
        nodes.add(new HostAndPort("39.105.93.97",7005));
    }

    public static void main(String[] args) {
        System.out.println(nodes.size());
        nodes.forEach(e -> System.out.println(e));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        String result = jedisCluster.set("mycluster", "test JedisCluster");
        System.out.println("the result value is "+result);

        String value = jedisCluster.get("mycluster");
        System.out.println(value);
    }
}
