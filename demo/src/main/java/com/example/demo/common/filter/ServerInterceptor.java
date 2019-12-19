package com.example.demo.common.filter;

import com.example.demo.common.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 使用注解标注过滤器
 * 
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器 属性filterName声明过滤器的名称,可选
 *                                                属性urlPatterns指定要过滤
 *                                                的URL模式,也可使用属性value来声明.(
 *                                                指定要过滤的URL模式是必选属性)
 * 
 * @author zhangrz
 * @create 2016年8月6日
 */
// @WebFilter(filterName = "serverFilter", urlPatterns = "/*")
public class ServerInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final String time_log_name = "finance-admin-method-time-consuming-name";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	/*	String ip = RequestUtil.getRemoteAddr(request);
		String uri = RequestUtil.trimURI(request);
		String account = BaseEntity.getCurrentUserAccount();
//		Map<String, String> map = RequestUtil.getAllParameter(request);
		Map<String, String> map = MapUtil.getUrlParameter(request);
		logger.info("ip地址{},请求方式{},访问地址{},当前登录帐号{},请求参数{}", ip, request.getMethod(), uri, account, map);
		request.setAttribute(time_log_name, System.currentTimeMillis());*/
		return true;
	}

	//@Override
/*	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		long endTime = System.currentTimeMillis();
		long startTime = (Long) request.getAttribute("100000");
		long time = endTime - startTime;
		if (time > 200) {
			logger.warn("method to detect timeout for {}, and the execution time is {} ms",
					RequestUtil.trimURI(request), time);
		}
	}*/

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet
		// 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}

}