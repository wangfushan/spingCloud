package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.config.SystemConfig;
import com.example.demo.common.util.AlipayUtil;
import com.example.demo.common.util.JsonHelper;
import com.example.demo.common.util.RestTemplateHelperV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/Rsa")
public class RsaTest {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/jiaMi")
    public String getData(String data) throws Exception {
        //没错 就是这么长
        String sign = SystemConfig.DRIVER_PRIVATE_KEY;
        Map<String, String> map = new HashMap<>();
        map.put("ss", data);

        String aa = SystemConfig.ACCOUNT_AGENT_PROFIT;
        //map转treeMap  排序
        Map<String, String> reqContent = new HashMap<String, String>();
        reqContent.put("name", "1.0"); //调用的接口版本，固定为：1.0
        reqContent.put("charset", "utf-8"); //请求使用的编码格式
        reqContent.put("signType", "RSA2");
        reqContent.put("timestamp", new Date().toString()); //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss
        Map<String, String> sortedMap = new TreeMap<String, String>(map);
        reqContent.put("bizContent", JSON.toJSONString(sortedMap)); //请求参数的集合
        //reqContent.put("notify_url", aliConfig.getNotify()); //支付宝服务器主动通知商户服务器里指定的页面 http/https路径。

        reqContent.put("sign", AlipayUtil.generateSignature(reqContent, sign));
        reqContent.remove("bizContent");
        Map<String, Object> map1 = new HashMap<>();
        map1.putAll(reqContent);
        //  map1.put("bizContent",map);
        //
        String a = restTemplate.postForObject("http://service-feign/RemitBankColorController/getService1", RestTemplateHelperV2.postForm1(map1), String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return a;
    }

}
