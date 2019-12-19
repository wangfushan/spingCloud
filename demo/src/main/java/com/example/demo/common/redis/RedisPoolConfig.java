package com.example.demo.common.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

@Component
public class RedisPoolConfig extends JedisPoolConfig {

	@Value("${redis.maxIdle}")
	private int maxIdle;
	@Value("${redis.minIdle}")
	private int minIdle;
	@Value("${redis.maxWaitMillis}")
	private int maxWaitMillis;
	@Value("${redis.maxTotal}")
	private int maxTotal;
	@Value("${redis.testOnBorrow}")
	private boolean testOnBorrow;

	public RedisPoolConfig() {
		super();
	}

	@PostConstruct
	private void init() {
		setMaxIdle(maxIdle);
		setMinIdle(minIdle);
		setMaxWaitMillis(maxWaitMillis);
		setMaxTotal(maxTotal);
		setTestOnBorrow(testOnBorrow);
	}

}
