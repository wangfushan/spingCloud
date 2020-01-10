package com.example.demo.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpClientUtils {

    public static String sendGet(String url, Map<String, String> headers, Object object) {
        return sendHttpRequest("GET", url + "?" + object2FormString(object), headers, "text/plain", "");
    }

    public static String sendFormPost(String url, Map<String, String> headers, Object object) {
        return sendHttpRequest("POST", url, headers, "application/x-www-form-urlencoded", object2FormString(object));
    }

    public static String sendJsonPost(String url, Map<String, String> headers, Object object) {
        return sendHttpRequest("POST", url, headers, "application/json", JSON.toJSONString(object));
    }

    public static String sendJsonDelete(String url, Map<String, String> headers, Object object) {
        return sendHttpRequest("DELETE", url, headers, "application/json", JSON.toJSONString(object));
    }

    public static String sendJsonPut(String url, Map<String, String> headers, Object object) {
        return sendHttpRequest("PUT", url, headers, "application/json", JSON.toJSONString(object));
    }

    public static String sendJsonPatch(String url, Map<String, String> headers, Object object) {
        return sendHttpRequest("PATCH", url, headers, "application/json", JSON.toJSONString(object));
    }

    public static String sendHttpRequest(String method, String url, Map<String, String> headers, String contentType, String content) {
        log.info("http client method:{},url:{},headers:{},contentType:{},content:{}", method, url, headers, contentType, content);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            RequestBuilder rb = RequestBuilder.create(method).setUri(url);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    rb.setHeader(entry.getKey(), entry.getValue());
                }
            }
            StringEntity paramEntity = new StringEntity(content, ContentType.create(contentType, "UTF-8"));
            rb.setEntity(paramEntity);
            HttpUriRequest req = rb.build();
            response = client.execute(req);
            String ret = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("http client method:{},url:{},response:{}", method, url, ret);
            return ret;
        } catch (Exception e) {
            //LogUtils.error(log, "http client error", e, method, url, headers, contentType, content);
            throw new RuntimeException("网络异常");
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
               // LogUtils.error(log, "http client error", e, method, url, headers, contentType, content);
            }
        }
    }

    private static String object2FormString(Object object) {
        Map<String, Object> params = JSON.parseObject(JSON.toJSONString(object));
        List<NameValuePair> paramList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            paramList.add(new BasicNameValuePair(entry.getKey(), String.valueOf(value)));
        }
        return URLEncodedUtils.format(paramList, StandardCharsets.UTF_8);
    }

}
