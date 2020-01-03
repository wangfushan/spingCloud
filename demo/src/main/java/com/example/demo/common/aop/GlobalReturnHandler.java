package com.example.demo.common.aop;


import com.example.demo.common.vo.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * 统一返回结果处理
 * @author xiang.zhang.o
 *
 */
@SuppressWarnings("rawtypes")
@RestControllerAdvice
public class GlobalReturnHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        List<String> type = request.getHeaders().get("out-type");
        if(CollectionUtils.isEmpty(type) || body instanceof Result || !"format".equals(type.get(0))) {
            return body;
        }
        return Result.successResult(body);
    }
}
