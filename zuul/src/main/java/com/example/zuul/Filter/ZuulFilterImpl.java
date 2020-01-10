package com.example.zuul.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Log4j2

@Component
public class ZuulFilterImpl extends ZuulFilter {
    private static final String token = "2019";  //可以将token设置成用户的某个信息 这样每次请求都可以确保是该用户的请求。
    @Override
    public String filterType() {
        //		pre：路由之前
        //		routing：路由之时
        //		post： 路由之后
        //		error：发送错误调用
        return "pre";
    }

    @Override
    public int filterOrder() {   //过滤器执行优先级，同filterType类型中，order值越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() { //这里可以写逻辑判断，是否要过滤，true为永远过滤。
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();//获取请求报文
        HttpServletRequest request = ctx.getRequest();//从报文中拿出请求
        Object object = request.getParameter("token");//拿出token值
        if (object == null || !object.equals(token)) {
            log.info("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                log.warn("拦截到非法请求");
                //设置编码格式，否则中文乱码
                ctx.getResponse().setContentType("text/html;charset=utf-8");
                ctx.getResponse().getWriter().write("token无效！");
            } catch (Exception e) {

            }
            return null;
        }
        log.info("OK");

        return null;
    }
}
