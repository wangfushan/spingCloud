package com.example.demo.common.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

/**
 * 
 * @author wenbin.dai
 *
 */
public class RequestUtil {

	/**
	 * 判断uri是不是以字符串数组中的元素为前缀
	 * 
	 * @param uri
	 * @param prefixArray
	 * @return
	 */
	public static boolean isStartsWith(String uri, String prefixArray) {
		if (StringUtils.isNotBlank(prefixArray)) {
			for (String prefix : prefixArray.split(",")) {
				if (uri.startsWith(prefix)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断uri是不是存在于字符串数组中
	 * 
	 * @param uri
	 * @param uriArray
	 * @return
	 */
	public static boolean isEqualsIn(String uri, String uriArray) {
		if (StringUtils.isNotBlank(uriArray)) {
			for (String s : uriArray.split(",")) {
				if (uri.equals(s)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 对uri中可能出现的多个斜杠去重
	 * 
	 * @param request
	 * @return
	 */
	public static String trimURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < uri.length(); i++) {
			char c = uri.charAt(i);
			if (c == '/') {
				if (flag) {
					continue;
				}
				flag = true;
			} else {
				flag = false;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	/**
	 * 获取request中所有参数
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllParameter(HttpServletRequest request) {
		Map<String, String> map = Maps.newHashMap();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = enu.nextElement();
			String value = request.getParameter(paraName);
			if (value != null) {
				try {
					value = URLDecoder.decode(value, StandardCharsets.UTF_8.name());
				} catch (UnsupportedEncodingException e) {
				}
			}
			map.put(paraName, value);
		}
		return map;
	}

}
