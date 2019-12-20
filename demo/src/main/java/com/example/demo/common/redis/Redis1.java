package com.example.demo.common.redis;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Log4j2
@Component
public class Redis1 {

    /**
     * 默认缓存时间
     */
    private   final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;// 单位秒 设置成一个钟

    private   final int DEFAULT_CACHE_NULL_SECONDS = 60; //单位秒，默认临时缓存null值一分钟，防缓存穿透

    /**
     * 随机加减时间
     */
    private   final int RANDOM_CACHE_SECONDS = 60 * 3 * 1;

    @Resource
    RedisTemplate<Object, Object> redisTemplate;

/*

    public Object get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }


    public void set(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }

    */
/**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     *//*

    public  Boolean exists(String key) {

        try {

            return redisTemplate.hasKey(key);
        } catch (Exception e) {

        } catch (Throwable e) {

        }
        return false;
    }
    */
/**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key     键 . <br/>
     * @param object  缓存对象 . <br/>
     * @param seconds 过期时间（单位为秒）.<br/>
     * @return true or false .
     *//*

    public  boolean set(String key, Object object, int seconds) {

        try {
            redisTemplate.opsForValue().set(key, object, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {

            return false;
        }
        return true;
    }

*/



    /**
     * 删除Redis中的所有key
     */
    public   void flushAll() {
        JedisRedisUtils.flushAll();
    }

    /**
     * 保存一个对象到Redis中(缓存过期时间:使用此工具类中的默认时间) . <br/>
     *
     * @param key    键 . <br/>
     * @param object 缓存对象 . <br/>
     * @return true or false . <br/>
     * @throws Exception
     */
    public   boolean save(Object key, Object object) {
        int timeout = DEFAULT_CACHE_SECONDS;
        if (object == null) {
            // 缓存null值较短时间，既防止null穿透，又防止新增信息不能及时刷新
            timeout = DEFAULT_CACHE_NULL_SECONDS;
        } else {
            // 保存数据时，默认过期时间加减一定时间，以防止热点数据集中失效
            timeout = timeout + RandomUtils.nextInt(RANDOM_CACHE_SECONDS);
        }

        return save(key, object, timeout);
    }


    /**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key     键 . <br/>
     * @param object  缓存对象 . <br/>
     * @param seconds 过期时间（单位为秒）.<br/>
     * @return true or false .
     */
    public   boolean save(Object key, Object object, int seconds) {
        log.debug("保存对象到redis, key：【{}】, 【{}】 【{}】", key, object, seconds);
        try {
            redisTemplate.opsForValue().set(key, object, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("redis保存异常", e);
            return false;
        }
        return true;
    }


    /**
     * 根据缓存键获取Redis缓存中的值.<br/>
     *
     * @param key 键.<br/>
     * @return Object .<br/>
     * @throws Exception
     */
    public   Object get(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param key
     * @return
     * @throws Exception
     */
    public   Boolean del(Object key) {
        log.debug("从redis删除数据, {}", key);
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("redis删除异常", e);
        }
        return true;
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public   Boolean del(Object... keys) {
        log.debug("从redis删除多个数据, {}", keys);
        try {
            redisTemplate.delete(keys);
        } catch (Exception e) {
            log.error("redis删除异常", e);
        }
        return true;
    }

    /**
     * @param key
     * @param seconds 超时时间（单位为秒）
     * @return
     */
    public   Boolean expire(Object key, int seconds) {
        log.debug("设置redis对象超时时间, {}, {}", key, seconds);
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 添加一个内容到指定key的hash中
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public   Boolean addHash(String key, Object field, Object value) {
        log.debug("添加一个内容到指定key的hash中, {}, {}, {}", key, field, value);
        try {
            redisTemplate.opsForHash().put(key, field, value);
        } catch (Exception e) {
            log.error("redis添加到hash异常", e);
        }
        return true;
    }

    /**
     * 从指定hash中拿一个对象
     *
     * @param key
     * @param field
     * @return
     */
    public   Object getHash(Object key, Object field) {
        log.debug("从指定hash中拿一个对象, {}, {}", key, field);
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 从hash中删除指定filed的值
     *
     * @param key
     * @param field
     * @return
     */
    public   Boolean delHash(Object key, Object field) {
        log.debug("从hash中删除指定filed的值, {}, {}", key, field);
        try {
            redisTemplate.opsForHash().delete(key, field);
        } catch (Exception e) {
            log.error("redis从hash删除异常", e);
        }
        return true;
    }

    /**
     * 拿到缓存中所有符合pattern的key
     *
     * @param pattern
     * @return
     */
    public Set<Object> keys(String pattern) {
        log.debug("拿到缓存中所有符合pattern的key, {}", pattern);
        return redisTemplate.keys(pattern);
    }

    /**
     * 获得hash中的所有key value
     *
     * @param key
     * @return
     */
    public Map<Object, Object> getAllHash(Object key) {
        log.debug("获得hash中的所有key value, {}", key);
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     */
    public   Boolean exists(Object key) {
        log.debug("判断一个key是否存在:s{}", key);
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("判断一个key是否存在异常, {}", e);
        } catch (Throwable e) {
            log.error("判断一个key是否存在异常, {}", e);
        }
        return false;
    }

    public   int getSize() {
        return JedisRedisUtils.getSize();
    }


    /**
     * 获取锁
     *
     * @return
     */
    public  boolean tryLock(String lockKey, String requestId, long timeout) {
        log.debug("请求获得锁：{}{}{}", lockKey, requestId, timeout);
        Boolean ret = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId);
        log.debug("获得锁结果：{}", ret);
        if (ret != null && ret) {
            redisTemplate.expire(lockKey, timeout, TimeUnit.SECONDS);
            log.debug("设置锁超时时间：{}{}", lockKey, timeout);
            return true;
        }
        return false;
    }

    /**
     * 解锁 需相同requestId才能解锁
     *
     * @param lockKey
     * @param requestId
     * @return
     */
    public  boolean unLock(String lockKey, String requestId) {
        log.debug("请求释放锁：{}{}", lockKey, requestId);
        Object value = redisTemplate.opsForValue().get(lockKey);
        log.debug("查询获得锁的值：{}", value);
        if (requestId != null && requestId.equals(value)) {
            redisTemplate.delete(lockKey);
            log.debug("释放锁成功：{}", lockKey);
            return true;
        }
        return false;
    }

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate = redisTemplate;
    }
}
