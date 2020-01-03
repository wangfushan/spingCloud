package com.example.demo.common.aop;


import com.example.demo.common.util.JsonHelper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Log4j2
@Component//（把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
//@Component,@Service,@Controller,@Repository注解的类，并把这些类纳入进spring容器中管理。
@Aspect
public class ColorSqlAsp {


    @Pointcut("@annotation(com.example.demo.common.annotation.AspectColor)")
    public void aspectBankPointCut() {
    }

    @Before("aspectBankPointCut()")
    public void colorAddSql(JoinPoint joinPoint) {

        log.info("添加sql****************");
        joinPoint.toString();
        String a = joinPoint.getThis().toString();
        log.info("添加sql 原参数为：{}", a);
        try {
            Object[] args = joinPoint.getArgs();//把参数分为数组
            for (Object arg : args) {
                if (arg instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) arg;
                    map.put("bankName", "中国工商银行");
                    log.info("数据切面添加数据,map={}", JsonHelper.parseToJson(map));
                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加sql失败，切面数据异常");
        }

    }


}
