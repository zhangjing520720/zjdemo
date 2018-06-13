package com.sdy.util.redis;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import redis.clients.jedis.*;


public class RedisPool {

	private static ShardedJedisPool pool;

	
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
	        config.setMaxWaitMillis(10000);

	        config.setMaxTotal(1000);
	        config.setMaxIdle(10);
	        config.setMinIdle(5);
	        Properties props = new Properties();
			props.load(RedisPool.class.getClassLoader().getResourceAsStream("redis.properties"));
	        JedisShardInfo jedisShardInfo1 = new JedisShardInfo(props.getProperty("redis.host"), Integer.valueOf(props.getProperty("redis.port")));
	        if(props.getProperty("redis.pass")!=null && !props.getProperty("redis.pass").equals("") ){
	         jedisShardInfo1.setPassword(props.getProperty("redis.pass"));
	        }
	        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
	        list.add(jedisShardInfo1);
	        pool = new ShardedJedisPool(config, list);

        } catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static ShardedJedis getJedisShardInfo() {
		return pool.getResource();
	}
	
	public static ShardedJedisPool getShardedJedisPool() {
		return pool;
	}


}
