package com.example.demo.common.filter;

import com.example.demo.common.util.RequestUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 自定义登录拦截filter
 *
 * Created by YJ on 2017/9/19.
 */
@Component("ssoLoginFilter")
public class SsoLoginFilter extends FormAuthenticationFilter implements EnvironmentAware {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static final String AJAX_HEADER = "XMLHttpRequest";
    public static String loginMethod; //是否使用sso方式登录

    @Override
    public void setEnvironment(Environment environment) {
        //通过Environment获取配置文件中的信息(解决spring不能注入的问题)
        loginMethod = environment.getProperty("login.method.sso");
    }

    //处理需要认证的请求
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = RequestUtil.trimURI(httpServletRequest);
        String ajaxHeader = httpServletRequest.getHeader("X-Requested-With");
        String acceptHeader = httpServletRequest.getHeader("Accept");
        String userAgent = httpServletRequest.getHeader("User-Agent");
        logger.info("User-Agent:{}"+userAgent);
        boolean isAjax=AJAX_HEADER.equals(ajaxHeader);
        //处理session过期后的异步请求
        if(isAjax){
            httpServletResponse.setHeader("Expires", "0");
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setHeader("Pragma", "No-cache");
            String anyString ="Expired Session";
            try
            {
                OutputStream output=response.getOutputStream();
                byte[] bytes = anyString.getBytes("utf-8");
                response.setContentLength(bytes.length);
                response.setContentType("application/html;charset=utf-8");
                output.write(bytes);
                output.flush();
                output.close();
                return false;
            }
            catch (Exception e)
            {
                //e.printStackTrace();
            }
        }
        /*if(uri.contains("peApp")){
            logger.info("App session 失效处理。。。");
            httpServletRequest.getRequestDispatcher("/appLogin").forward(request,response);
            return false;
        }*/
        logger.info("使用sso认证:{}",loginMethod);
        if ("true".equals(loginMethod)) {
            request.getRequestDispatcher("/oauth_authentication").forward(request, response);
        } else {
            return super.onAccessDenied(request, response);
        }
        //不再继续处理
        return false;
    }
}
