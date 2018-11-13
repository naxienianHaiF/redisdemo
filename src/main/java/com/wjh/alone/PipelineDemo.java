package com.wjh.alone;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;

public class PipelineDemo {

    private static  Jedis jedis = null;

    public static void main(String[] args) throws IOException {
        String host = "39.105.93.97";
        int port = 6378;
        String auth = "naxienian1992";
        jedis = JedisUtiles.getJedis(host, port, auth);

        Pipeline pipelined = jedis.pipelined();

        for (int i=0;i<3;i++){
            pipelined.set("redis.pipeline."+i,  "pipeline.value."+i);
        }
        pipelined.sync();
        pipelined.close();

    }
}
