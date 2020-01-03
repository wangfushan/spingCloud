/**
 * Project Name:vom-paycallback
 * File Name:CommonAdvice.java
 * Package Name:com.nextev.config
 * Date:2017年5月11日上午10:40:42
 * Copyright (c) 2017, China Link Communications LTD All Rights Reserved.
 *
 */

package com.example.demo.common.aop;

import com.example.demo.common.util.RequestUtil;
import com.example.demo.common.vo.Result;
import com.example.demo.common.vo.ResultCode;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * ClassName: CommonAdvice <br/>
 * Date: 2017年5月11日 上午10:40:42 <br/>
 * Description: TODO
 *
 * @author dongshun.wang.o
 * @version
 * @see
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = Exception.class)
	public Object handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		String ip = RequestUtil.getRemoteAddr(request);
		String uri = RequestUtil.trimURI(request);
		log.error(String.format("ip地址%s,访问地址%s", ip, uri), exception);
		Result<String> result = Result.result(ResultCode.RESULT__ERROR_2);
		// http请求方法异常，抛出异常信息;request绑定参数异常，抛出异常信息;servlet异常抛出
		if (exception instanceof ServletException) {
			result.setResultDesc(exception.getLocalizedMessage());
		}
		// request body错误
		else if (exception instanceof HttpMessageConversionException) {
			String desc = exception.getLocalizedMessage();
			if (desc != null && desc.contains(":")) {
				desc = desc.substring(0, desc.indexOf(":"));
			}
			result.setResultDesc(desc);
		} else if (exception instanceof BindException) {
			BindException bindException = (BindException) exception;
			result.setResultDesc(bindException.getFieldError().getDefaultMessage());
		} else if (exception instanceof UnauthorizedException) {
			result.setResultDesc(String.format("无权限，请联系管理员(%s)", exception.getMessage()));
		} else if (exception instanceof IllegalArgumentException) {
			result.setResultDesc(String.format("错误信息:%s", exception.getMessage()));
		} else if (exception instanceof SQLException || exception.getCause() instanceof SQLException || exception instanceof MySQLSyntaxErrorException) {
			result.setResultDesc("查询条件错误");
		} else if (exception instanceof RuntimeException) {
			result.setResultDesc(exception.getLocalizedMessage());
		}
		return result;
	}

}
