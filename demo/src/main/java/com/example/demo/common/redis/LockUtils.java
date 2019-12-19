package com.example.demo.common.redis;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 */
@Log4j2
public class LockUtils {

    private static RedisTemplate redisTemplate;

    /**
     * 获取锁
     *
     * @return
     */
    public static boolean tryLock(String lockKey, String requestId, long timeout) {
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
    public static boolean unLock(String lockKey, String requestId) {
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
        LockUtils.redisTemplate = redisTemplate;
    }
}
