package com.example.demo.controller;


import com.example.demo.common.config.RabbitMqConfig;
import com.example.demo.common.config.RestTemplateConfig;
import com.example.demo.common.redis.Redis1;
import com.example.demo.common.util.*;
import com.example.demo.common.vo.SmsSandSendPo;
import com.example.demo.entity.RemitBankColor;
import com.example.demo.rabbitMq.easyMQ.publisher;
import com.example.demo.service.RemitBankColorService;
import com.example.demo.service.TSmsSandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/RemitBankColorController")
public class RemitBankColorController {

    @Autowired
    private RemitBankColorService remitBankColorService;
    @Autowired
    RedisTemplate<String, String> stringRedisTemplate;


    @Autowired
    private publisher publisher;
    @Resource
    Redis1 redis1;
    @Autowired
    private ThreadPoolTaskExecutor notifyServiceThreadPool;
    @Autowired
    RestTemplate restTemplate;


    @PostMapping(value = "/aaa")
    public void smsSend() {
    /*    SmsSandSendPo a=new SmsSandSendPo("15093261560","张碗 大瓷碗");
        SmsSendUtil b=new SmsSendUtil();*/
        notifyServiceThreadPool.execute(new TSmsSandService(new SmsSandSendPo("18568507564", "傻货 在家干啥了 家里不是解禁了？没出去浪啊？猜猜我是谁 别回短信  回微信")));
    }


    @PostMapping(value = "/now")
    public String async() {

        Date date = new Date();

        try {
            String aa = remitBankColorService.aasync(date);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            Date date1 = new Date();
            String dateString1 = formatter.format(date1);
            return dateString+":---------:"+dateString1;
        } catch (InterruptedException e) {
            return "报错";
        }

    }

    @PostMapping(value = "/async")
    public String async1() {

        Date date = new Date();

        try {
            String aa = remitBankColorService.aasynca(date);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            Date date1 = new Date();
            String dateString1 = formatter.format(date1);
            return dateString+":---------:"+dateString1;
        } catch (InterruptedException e) {
            return "报错";
        }

    }

    @PostMapping(value = "/listBankColor")
    public void listRemit(@RequestParam("typeCode") String typeCode) {
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        Map<String, Object> map = new HashMap<>();
        map.put("typeCode", typeCode);
        remitBankColorService.runv1();
    }

    @PostMapping(value = "/sevaList")
    public void saveList() {
        List list = new ArrayList();
        for (int i = 0; i <= 5000; i++) {
            RemitBankColor a = new RemitBankColor();
            a.setId(String.valueOf(i) + "q");
            a.setBankLogo("BankLogo");
            a.setTypeCode("222");
            a.setBankName("bankName");
            a.setRgb("rgb");
            a.setBankType("banktype");
            list.add(a);
        }

        remitBankColorService.saveList(list);


    }


    @PostMapping(value = "/listBankColorIn")
    public List<RemitBankColor> listBankColorIn(@RequestParam("typeCode") String typeCode, @RequestParam("a") String a, @RequestParam("b") String b) {

        List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return remitBankColorService.listRemit(typeCode, list);
    }
/*    @PostMapping(value = "/getFeign")
    public String getFeign(){
        Map<String,String> map=new HashMap<>();
        map.put("name","name");
        String name="name";
        String a=restTemplate.("http://service-feign/RemitBankColorController/getService1?name={name}",null,String.class,name);
        return  a;
    }*/


        @PostMapping(value = "/getFeign")
    public String getFeign() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "name");
        String name = "name";
        String a = restTemplate.postForObject("http://service-feign/RemitBankColorController/getService1", RestTemplateHelperV2.postForm(map), String.class);
        return a;
    }


    @PostMapping(value = "/getFeign6")
    public String getFeign6() {
      /*  Map<String,String> map=new HashMap<>();
        map.put("name","name");
        String name="name";

        String a=RestTemplateHelper.getForm("http://service-feign/RemitBankColorController/getService1",map);*/
        String name = "name";
        return remitBankColorService.getHystrix(name);
    }

    public String postForm(String url, Map<String, String> map) throws RestClientException {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        if (MapUtils.isNotEmpty(map)) {
            map.forEach(requestEntity::set);
        }

        return restTemplate.postForObject(url, requestEntity, String.class);
    }

    @PostMapping(value = "/getFeign7")
    public String getFeign7() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "name");

        RestTemplate cc = RestTemplateConfig.restTemplate();
        String data=cc.postForObject("http://service-feign/RemitBankColorController/getService1",map,String.class);
        return data;
    }
   /* @PostMapping(value = "/getFeign4")
    public String getFeign4(){
        Map<String,String> map=new HashMap<>();
        map.put("name","name");
        String name="name";
        String a= OKHttp3Util.get("https://httpbin.org/anything",map);
       // String a=getForm("https://httpbin.org/anything",map);
        return  a;
    }*/


    @PostMapping(value = "/getFeign4")
    public String getFeign4() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "name");

        RestTemplate cc = RestTemplateConfig.restTemplate();
        String a = cc.getForObject("https://httpbin.org/anything", String.class, map);
        // String a=getForm("https://httpbin.org/anything",map);
        return a;
    }

    @PostMapping(value = "/getFeign41")
    public String getFeign41() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "name");
        String name = "name";
        RestTemplate cc = RestTemplateConfig.restTemplate();
        String data=cc.postForObject("http://192.168.13.209:8080/gateway/appPayment/payMent",map,String.class);
        if (data.isEmpty()){
            return null;
        }else {

            Map<String,Object> map1=JsonHelper.parseToMap(data);
            String id=map1.get("id").toString();
            String zjhm=map1.get("zjhm").toString();
            String zp=map1.get("zp").toString();
            String zprq=map1.get("zprq").toString();
            String sjgxsj=map1.get("sjgxsj").toString();
            String photo_no=map1.get("photo_no").toString();
            String jlrksj=map1.get("jlrksj").toString();
            String rid=map1.get("rid").toString();
            Blob b=null;
            try {
               b = new SerialBlob(zp.getBytes("GBK"));//String 转 blob
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return  "接下把数据入库就行了 ";

        }
    }

    public String getForm(String url, Map<String, String> map) {
        if (MapUtils.isNotEmpty(map)) {
            StringBuilder sb = new StringBuilder(url);
            sb.append("?");
            map.forEach((key, value) -> {
                sb.append(key).append("=").append(value).append("&");
            });
            url = sb.toString();
        }
        return restTemplate.getForObject(url, String.class);
    }


    public String getPostForm(String url, Map<String, String> map) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        if (MapUtils.isNotEmpty(map)) {
            map.forEach(requestEntity::set);
        }
        return restTemplate.postForObject(url, requestEntity, String.class);

    }


    @PostMapping(value = "/getFeign1")
    public String getFeign1() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "name");

        String a = restTemplate.getForObject("http://service-feign/RemitBankColorController/getService1", String.class, JsonHelper.parseToJson(map));
        return a;
    }

    @PostMapping(value = "/getFeign2")
    public String getFeign2() {
        String name = "ss";
        String a = restTemplate.getForObject("http://service-feign/RemitBankColorController/getService1?name=" + name, String.class);
        return a;
    }


    @PostMapping(value = "/getList")
    public Map<String, Object> getList(String typeCode) {

        if (stringRedisTemplate.hasKey("aaa11a11")) {
            Map<Object, Object> resultMap = stringRedisTemplate.opsForHash().entries("aaa11a11");
            String a = JsonHelper.parseToJson(resultMap);
            return JsonHelper.parseToMap(a);
        } else {
            Map<String, Object> map = remitBankColorService.list(typeCode);
            Map<String, String> mapa = new HashMap<>();
            mapa.put("s", "s");
            stringRedisTemplate.opsForHash().putAll("aaa11a11", mapa);
            return map;
        }
    }


    @PostMapping(value = "/getList1")
    public Map<String, Object> getList1(String typeCode) {

        if (redis1.exists("11")) {
            Map<Object, Object> resultMap = (Map<Object, Object>) redis1.get("11");
            String a = JsonHelper.parseToJson(resultMap);
            publisher.send();
            return JsonHelper.parseToMap(a);
        } else {
            Map<String, Object> map = remitBankColorService.list(typeCode);
            // template.opsForHash().putAll("myCache",map);
            redis1.save("11", map, 111);
            publisher.send();
            return map;
        }
    }

    @PostMapping(value = "/getList2")
    public List getList2(String typeCode) {

        if (redis1.exists("111")) {
            return (List) redis1.get("111");
        } else {
            //Map<String, Object> map = remitBankColorService.list(typeCode);
            // template.opsForHash().putAll("myCache",map);
            List list = new ArrayList();
            //bulist.add(map.get("a"));
            list.add(1);
            list.add(1);
            list.add(1);
            redis1.save("111", list, 111);
            return list;
        }
    }

    @PostMapping(value = "/getList3")
    public String getList3(String typeCode) {

        if (redis1.exists("111")) {
            return (String) redis1.get("111");
        } else {
            Map<String, Object> map = remitBankColorService.list(typeCode);
            // template.opsForHash().putAll("myCache",map);
            if (MapUtils.getString(map, "bankName") == null) {
                map.put("bankName", "sss");
            }

            redis1.save("111", map.get("bankName"), 111);
            return (String) map.get("bankName");
        }
    }

}
