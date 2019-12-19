package com.example.demo.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Redis缓存客户端连接池
 * 
 */
@Service
public class RedisClientPool {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JedisPoolConfig jedisPoolConfig; // 连接池配置

	@Value("${redis.server}")
	private String redisServer;

	private ShardedJedisPool jedisPool; // 客户端连接池

	@PostConstruct
	private void initialPool() {
		logger.info("****初始化Redis客户端****");
		List<JedisShardInfo> list = new ArrayList<>();
		String[] arr = redisServer.split(";");
		for (String ipAndPort : arr) {
			arr = ipAndPort.split(":");
			String ip = arr[0];
			String port = arr[1];
			logger.info("Redis服务器地址：{}:{}", ip, port);
			list.add(new JedisShardInfo(ip, Integer.parseInt(port)));
		}
		jedisPool = new ShardedJedisPool(jedisPoolConfig, list);
	}

	public ShardedJedis getClient() {
		return jedisPool.getResource();
	}

}
