package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.util.AlipayUtil;
import com.example.demo.common.util.JsonHelper;
import com.example.demo.common.util.RestTemplateHelperV2;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getData(String data)throws Exception {
        //没错 就是这么长
       String sign="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3NoiqoV9UQOZbCH9z5yKKmLmZturVJiEZAmOYvGL/eExy9GGqEZy1jLeSEajpjYKdsFdk4j8MWQnCl" +
               "k8NuX4sIhf6zKqDtMhX7eMt54RFMJJUZig14WK1j7b3c3PenjBbcSOGeMkfOcokdmVi675tnc3cqVaSKcwzlbqdyRCnV98pf6a0UGO1hqKzvMSj/egdUjBWQJMAE4Vzpaj9iz8gcqUE" +
               "0nH5bUU0aUx5+I1adHW3iPqywESqKyrr7K7U8DyCk76/e1uyj8TJkohe/okphL796kFfRTA8aUzg1O418vEmkqjU+QTuEQZZcsanmEPA0iwV7Am6xicbmes3A4wrAgMBAAECggEAdMsM4Eky" +
               "7hDZSBrotMgZWuT1HCphFTPTUZAWUZ12NolHothrEfFcRtfHhhQ/63LMWx24+JTJwGNaN3N+Pa+L4TJyuuyRnpg2RuddNTwrM7g88BSXeZGOTVOqPaCZssaA6Sn8jZKeE4vRcuEOzLqfkF1SiGJiz5Z" +
               "ZmGDQsBkmxqXFUq+yzk71cdgCjQjiKByS1yv40cWqW5KFbiqzRM2lQG3f4cOsAUKsYGgMkZ/TC4A1ADa9ZDaE8MZqr7AdpPp8yuUrnwr059RS7OLRkJ+L/T9hpZB/+QMFdav/OSuAhWnG8+QOOlSxU4g+oV0" +
               "r20yaQ5fXi/XSrvuCHu5QPpRwYQKBgQD1cGseus1xx8CqX9DTNf0oDJjtw2tmp70hY+jsrBIUCsznxV7xyiEStQ1BYcsHcsf1fWUiy2iCe53mSDjTaQv5ejuykYcXc/Hx5nrpc4nqgx/5Bz+tqrQnWwRD7xJx5WYe" +
               "QBAJ/Z+sughwG+VCuhM1sVyqtAJ2V78Qt4422mIGIwKBgQC/GK1bdqnhqUHt9BbK2zefmkvSGK5+rjeGp2IJtpX3wlRNGixlq8ICXU/UxNb1yY4UjMQ69QIJyAc/erdRMzKyZl3RDxFAJYKczBc50TwYQQWkfRfiaVy588" +
               "4t9dk4kwItitWyt4Q3LIk0a+D9m3ryb5OJOh+a7l5mPkwAKtiOWQKBgCJFWz/EN7GegcGIdYE4davk7mmsss93ton2CG0brb6Mr4XhYluCrNGO5FXv0Miud0tJAhk0q9gkaKziAsVKUri4H6PUfDeDIhJz60+zqVtMiedc4" +
               "sBeKJ+iLttJg5rRvEuXCGZMusIEi+8ThhSmJM6HgHf5gKqDoBM2fi4cmjlfAoGBAJac131gEFLYOStXl2M41iVH0fx8EYOswfyyDhYDvz0v/4tpVpangdilPNdAcP/1ZLMTo1Goa3vUGMpSaQtpo8px3f14t6rsJX55aNDb" +
               "9VZUYSMcqOd9DWVOIDGfNB5f13aXlwgtldp6koUrhX+8vHE5uXmNwv00ZFiHPxVer6G5AoGBAIpR4btksEEDOxyuPQgXhRsB7hXsJ4xvmtr0AMPeafWau5Sfo9" +
               "75v0mYKvkVax5PBoz8xVTMvisEWdTuX7g0TYy7zLhpwEDK1bh83Nu09tPgW3jOL8HT0aaanqE6tvMogmvNFHxQtvN1FTvnzlaTIwtpllL2tag3cSKwpDwGq9NX";
        Map<String,String> map=new HashMap<>();
        map= JsonHelper.parseToMap(data);

        //map转treeMap  排序
        Map<String, String> reqContent = new HashMap<String, String>();
        reqContent.put("version","1.0"); //调用的接口版本，固定为：1.0
        reqContent.put("charset", "utf-8"); //请求使用的编码格式
        reqContent.put("signType", "RSA2");
        reqContent.put("timestamp", new Date().toString()); //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss
        Map<String, String> sortedMap = new TreeMap<String, String>(map);
        reqContent.put("bizContent", JSON.toJSONString(sortedMap)); //请求参数的集合
        //reqContent.put("notify_url", aliConfig.getNotify()); //支付宝服务器主动通知商户服务器里指定的页面 http/https路径。

        reqContent.put("sign", AlipayUtil.generateSignature(reqContent,sign));
        reqContent.remove("bizContent");
        Map<String,Object> map1=new HashMap<>();
        map1.putAll(reqContent);
        map1.put("bizContent",map);
       //
        String a = restTemplate.postForObject("http://service-feign/RemitBankColorController/getService1", RestTemplateHelperV2.postForm1(map1), String.class);

        return a;
    }

}
