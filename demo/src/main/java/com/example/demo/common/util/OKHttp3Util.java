package com.example.demo.common.util;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * (C) Shanghai Sand Information Technology System Co., Ltd.
 * All Rights Reserved.
 * <p>
 * Description: OKHttp3请求工具
 * <p>
 * Modification History: <p>
 * ============================================================================= <p>
 * Author         Date          Modification Description <p>
 * ------------- ---------- --------------------------------------------------- <p>
 * Wu.WQ         2018/5/28        Create <p>
 * ============================================================================= <p>
 */
public class OKHttp3Util {
    private static final Logger LOG = LogManager.getLogger(OKHttp3Util.class);

    private static final long CONNECT_TIMEOUT = 60;
    private static final long WRITE_TIMEOUT = 60;
    private static final long READ_TIMEOUT = 30;
    /**
     * post请求使用{@link OKHttp3Util#post(String, Map, int)}
     */
    public static final int TYPE_JSON = 1;
    /**
     * post请求使用{@link OKHttp3Util#post(String, Map, int)}
     */
    public static final int TYPE_FORM = 2;
    /**
     * post请求不支持使用{@link OKHttp3Util#post(String, Map, int)}
     */
    public static final int TYPE_XML = 3;

    public static final int TYPE_XML2 = 4;

    public static final int TYPE_TEXT = 5;

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final MediaType FORM = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");

    private static final MediaType XML = MediaType.get("application/xml; charset=utf-8");

    private static final MediaType XML2 = MediaType.get("text/xml; charset=utf-8");

    private static final MediaType TEXT = MediaType.get("text/plain; charset=utf-8");

    public static final OkHttpClient okHttpClient;
    public static final OkHttpClient okHttpClientNoProxy;

    static {
        String ip="172.17.2.19";
        okHttpClient = new OkHttpClient.Builder()
               // .proxy(SystemConfig.PROXY_ENABLE ? new Proxy(Proxy.Type.HTTP, new InetSocketAddress(SystemConfig.PROXY_IP, SystemConfig.PROXY_PORT)) : Proxy.NO_PROXY)
                .proxy(false? new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, 0)) : Proxy.NO_PROXY)
                //.proxySelector(new ProxySelectorImpl())//代理选择器
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
        okHttpClientNoProxy = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * get请求方法,无参
     *
     * @param url 请求地址
     * @return 响应body
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * get请求，带参
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应body.string
     */
    public static String get(String url, Map<String, String> params) {
        Request request = getRequest(url, params);
        return execute(request);
    }

    /**
     * get请求，带参
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param params  请求参数
     * @return 响应直接解析成class对应类
     */
    public static String get(String url, Map<String, String> headers, Map<String, String> params) {
        Request request = getRequest(url, headers, params);
        return execute(request);
    }

    private static Request getRequest(String url, Map<String, String> params) {
        return getRequest(url, null, params);
    }

    private static Request getRequest(String url, Map<String, String> headMap, Map<String, String> params) {
        LOG.info("===============OkHttp3Client-get===============");
        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (params != null) {
            params.forEach(builder::addQueryParameter);
        }
        HttpUrl httpUrl = builder.build();
        LOG.info("===============请求地址:" + httpUrl);
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headMap != null) {
            headMap.forEach(headersBuilder::add);
        }
        Headers headers = headersBuilder.build();
        LOG.info("===============请求头:{}", headers);

        return new Request.Builder().url(httpUrl).headers(headers).build();
    }

    /**
     * post请求
     *
     * @param url  请求地址
     * @param data 请求参数Map集合
     * @param type 请求参数类型 {@value OKHttp3Util#TYPE_JSON} 和 {@value OKHttp3Util#TYPE_FORM}
     * @return 返回请求结果
     */
    public static String post(String url, Map<String, String> data, int type) {
        Request request = mapPostRequest(url, data, type);
        return execute(request);
    }

    /**
     * post请求
     *
     * @param url  请求地址
     * @param data 请求参数Map集合
     * @param type 请求参数类型 {@value OKHttp3Util#TYPE_JSON} 和 {@value OKHttp3Util#TYPE_FORM}
     * @return 返回请求结果
     */
    public static String postNoProxy(String url, Map<String, String> data, int type) {
        Request request = mapPostRequest(url, data, type);
        return executeNoProxy(request);
    }

    /**
     * Post JSON 请求
     *
     * @param url      请求地址
     * @param jsonData 请求参数
     * @return 结果
     */
    public static String postJson(String url, String jsonData) {
        Request request = stringPostRequest(url, TYPE_JSON, jsonData);
        return execute(request);
    }


    /**
     * Post JSON 短信内网请求
     *
     * @param url      请求地址
     * @param jsonData 请求参数
     * @return 结果
     */
    public static String postJsonNoProxy(String url, String jsonData) {
        Request request = stringPostRequest(url, TYPE_JSON, jsonData);
        return executeNoProxy(request);
    }

    /**
     * Post XML 请求
     *
     * @param url     请求地址
     * @param xmlData 请求参数
     * @return 结果
     */
    public static String postXml(String url, String xmlData) {
        Request request = stringPostRequest(url, TYPE_XML, xmlData);
        return execute(request);
    }

    /**
     * Post XML 请求
     *
     * @param url     请求地址
     * @param xmlData 请求参数
     * @return 结果
     */
    public static String postXml2(String url, String xmlData) {
        Request request = stringPostRequest(url, TYPE_XML2, xmlData);
        return execute(request);
    }

    /**
     * Post Form 请求
     *
     * @param url  请求地址
     * @param data 请求参数Map集合
     * @return 响应结果
     */
    public static String postForm(String url, Map<String, String> data) {
        return post(url, data, TYPE_FORM);
    }

    /**
     * Post Form 请求
     *
     * @param url  请求地址
     * @param data 请求参数Map集合
     * @return 响应结果
     */
    public static String postFormNoProxy(String url, Map<String, String> data) {
        return postNoProxy(url, data, TYPE_FORM);
    }

    /**
     * Post Form 请求
     *
     * @param url  请求地址
     * @param data 请求参数Map集合
     * @return 响应结果
     */
    public static String postNoProxy(String url, String data,int type) {
        Request request = stringPostRequest(url, type, data);
        return executeNoProxy(request);
    }

    /**
     * post请求
     *
     * @param url     请求地址
     * @param headers 请求头Map集合
     * @param data    请求参数Map集合
     * @param type    请求参数类型 {@value OKHttp3Util#TYPE_JSON} 和 {@value OKHttp3Util#TYPE_FORM}
     * @return 返回请求结果
     */
    public static String postAddHeaders(String url, Map<String, String> headers, Map<String, String> data, int type) {
        Request request = mapPostAddHeadersRequest(url, headers, data, type);
        return execute(request);
    }

    /**
     * Post JSON 请求
     *
     * @param url      请求地址
     * @param headers  请求头Map集合
     * @param jsonData 请求参数Map集合
     * @return 结果
     */
    public static String postJsonAddHeaders(String url, Map<String, String> headers, String jsonData) {
        Request request = stringPostAddHeadersRequest(url, headers, TYPE_JSON, jsonData);
        return execute(request);
    }

    /**
     * Post Form 请求
     *
     * @param url     请求地址
     * @param headers 请求头Map集合
     * @param data    请求参数Map集合
     * @return 响应结果
     */
    public static String postFormAddHeaders(String url, Map<String, String> headers, Map<String, String> data) {
        return postAddHeaders(url, headers, data, TYPE_FORM);
    }

    private static Request stringPostRequest(String url, int type, String data) {
        return stringPostAddHeadersRequest(url, null, type, data);
    }

    private static Request stringPostAddHeadersRequest(String url, Map<String, String> headers, int type, String data) {
        LOG.info("===============OkHttp3Client-post===============");
        LOG.info("===============请求地址:" + url);
        LOG.info("===============请求内容:" + data);
        RequestBody body = null;
        if (TYPE_JSON == type) {
            body = RequestBody.create(JSON, data);
        } else if (TYPE_FORM == type) {
            body = RequestBody.create(FORM, data);
        } else if (TYPE_XML == type) {
            body = RequestBody.create(XML, data);
        } else if (TYPE_XML2 == type) {
            body = RequestBody.create(XML2, data);
        } else if (TYPE_TEXT == type) {
            body = RequestBody.create(TEXT, data);
        } else {
            throw new IllegalArgumentException("无效的MediaType");
        }

        Request.Builder builder = new Request.Builder().url(url).post(body);
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(builder::header);
        }
        return builder.build();
    }

    private static Request mapPostRequest(String url, Map<String, String> data, int type) {
        return mapPostAddHeadersRequest(url, null, data, type);
    }

    public static Request mapPostAddHeadersRequest(String url, Map<String, String> headers, Map<String, String> data, int type) {
        LOG.info("===============OkHttp3Client-post===============");
        LOG.info("===============请求地址:" + url);
        LOG.info("===============请求内容:{}", data);
        RequestBody body = null;
        if (TYPE_JSON == (type)) {
            body = RequestBody.create(JSON, JacksonUtil.obj2json(data));
        } else if (TYPE_FORM == type) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            data.forEach(bodyBuilder::add);
            body = bodyBuilder.build();
        } else {
            throw new IllegalArgumentException("无效的MediaType");
        }

        Request.Builder builder = new Request.Builder().url(url).post(body);
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(builder::header);
        }
        return builder.build();
    }

    /**
     * 执行请求，返回string
     *
     * @param request 请求
     * @return string结果
     */
    private static String execute(Request request) {
        Response response = null;
        ResponseBody body = null;
        try {
            response = okHttpClient.newCall(request).execute();
            LOG.info("===============请求返回状态码:" + response.code());
            if (response.isSuccessful()) {
                body = response.body();
                String resp = body == null ? "" : body.string();
                LOG.info("===============请求响应成功:{}", resp);
                return resp;
            } else {
                LOG.info("===============请求响应失败:{}", response);
                throw new RuntimeException("响应失败");
            }
        } catch (IOException e) {
            LOG.error("===============请求异常:{}", e.getMessage());
            throw new RuntimeException("请求异常");
        }
    }

    /**
     * 执行请求，返回string
     *
     * @param request 请求
     * @return string结果
     */
    private static String executeNoProxy(Request request) {
        Response response = null;
        ResponseBody body = null;
        try {
            response = okHttpClientNoProxy.newCall(request).execute();
            LOG.info("===============请求返回状态码:" + response.code());
            if (response.isSuccessful()) {
                body = response.body();
                String resp = body == null ? "" : body.string();
                LOG.info("===============请求响应成功:{}", resp);
                return resp;
            } else {
                LOG.info("===============请求响应失败:{}", response);
                throw new RuntimeException("响应失败");
            }
        } catch (IOException e) {
            LOG.error("===============请求异常:{}", e.getMessage());
            throw new RuntimeException("请求异常");
        }
    }
}
