package com.example.zuul.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
/*注入到spring中  一定要加！一定要加！一定要加！*/
@Component
public class MyFilter extends ZuulFilter {

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
    public int filterOrder() {
        //过滤器执行优先级，同filterType类型中，order值越大，优先级越低

        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //这里可以写逻辑判断，是否要过滤，true为永远过滤。
        return true;
    }

    /*过滤器方法*/
    @Override
    public Object run() throws ZuulException {
        log.info("开始过滤");

        RequestContext ctx =RequestContext.getCurrentContext();
        HttpServletRequest request =ctx.getRequest();
        String tokens =request.getParameter("token");
        /*可以将token的值设置成用户名或者一些能代表该用户的值*/
        if (tokens == null||!token.equals(tokens)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                log.warn("拦截到非法请求");
                //设置编码格式，否则中文乱码
                ctx.getResponse().setContentType("text/html;charset=utf-8");
                ctx.getResponse().getWriter().write("非法请求");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        log.info("过滤结束");
        return null;
    }
}
