package com.example.demo.common.filter;

import com.example.demo.common.filter.wrapper.XssAndSqlHttpServletRequestWrapper;
import com.example.demo.common.util.RequestUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器
 * 
 * @author wenbin.dai
 *
 */
@WebFilter(filterName = "serverFilter", urlPatterns = "/*")
public class ServerFilter implements Filter {

	private PathMatcher matcher = new AntPathMatcher();

	private List<String> ignoreList = new ArrayList<>();

	{
		ignoreList.add("/Assets/**");
		ignoreList.add("/ATE/**");
		ignoreList.add("/loginPage/**");
		ignoreList.add("/financeProduct/**");
		ignoreList.add("/plugins/**");
		ignoreList.add("/login");
	}

	private boolean isIgnore(String uri) {
		for (String ignore : ignoreList) {
			if (matcher.match(ignore, uri)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse reponse = (HttpServletResponse) servletResponse;
		String uri = RequestUtil.trimURI(request);
		if (!isIgnore(uri) && uri.equals("/login")) {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				reponse.sendRedirect("/login");
				return;
			}
		}
		XssAndSqlHttpServletRequestWrapper xssFilterRequest = new XssAndSqlHttpServletRequestWrapper(request);
		chain.doFilter(xssFilterRequest, servletResponse);
	}

	@Override
	public void destroy() {
	}

}
