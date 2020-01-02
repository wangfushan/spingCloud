package com.example.demo.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.util.JsonHelper;
import com.example.demo.common.util.MapUtil;
import com.example.demo.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 接口日志记录
 * @author xiang.zhang.o
 *
 */
@Component
@Aspect
@Slf4j
public class LogInterceptor {

    @Resource
    private HttpServletRequest request;

    @Pointcut("execution(* com.example.demo.controller*..*(..))")
    public void log() {
    }

    @Before("log()")
    public void beforeMethod(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append("IN  IP:").append(RequestUtil.getRemoteAddr(request)).append(",Url:").append(RequestUtil.trimURI(request)).append(",Account:")
            ./*append(BaseEntity.getCurrentUserAccount()).*/append(",").append(getController(joinPoint)).append(getMethod(joinPoint)).append(",Params:")
            .append(JsonHelper.parseToJson(MapUtil.getUrlParameter(request)));
        log.info(sb.toString());
    }

    @AfterReturning(pointcut = "log()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        StringBuilder sb = new StringBuilder();
        sb.append("OUT ").append(getController(joinPoint)).append(getMethod(joinPoint)).append(getReturn(returnValue));
        log.info(sb.toString());
    }

    private String getController(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer().append("Controller: ").append(joinPoint.getTarget().getClass().getName());
        if (log.isDebugEnabled()) {
            sb.append(".(").append(joinPoint.getTarget().getClass().getSimpleName()).append(".java:1)");
        }
        return sb.toString();
    }

    private String getMethod(JoinPoint joinPoint) {
        return new StringBuffer().append(",Method: ").append(joinPoint.getSignature().getName()).toString();
    }

    private String getReturn(Object returnValue) {
        StringBuffer sb = new StringBuffer();
        String returnJSON = "";
        returnJSON = JSONObject.toJSONString(returnValue);
        sb.append(",Return: " + returnJSON);
        return sb.toString();
    }

}
