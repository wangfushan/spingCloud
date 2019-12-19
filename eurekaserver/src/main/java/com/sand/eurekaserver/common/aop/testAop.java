package com.sand.eurekaserver.common.aop;

import com.sand.eurekaserver.common.util.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Aspect
@Component
public class testAop {

    @Pointcut("@annotation(com.sand.eurekaserver.common.annotation.sql)")
    public void aspectBankPointCut() {
    }

    @SuppressWarnings("unchecked")
    @Before("aspectBankPointCut()")
    public void addCurrentAdminInfo(JoinPoint joinPoint) {
        log.info("进入addCurrentAdminInfo切面");
        try {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) arg;
                    map.put("bankId", "1");
                    map.put("cityId", "2");
                    log.info("数据切面添加数据,map={}", JsonHelper.parseToJson(map));
                } else {
                    return;
                }
            }
        } catch (Exception e) {

        }
    }


}
