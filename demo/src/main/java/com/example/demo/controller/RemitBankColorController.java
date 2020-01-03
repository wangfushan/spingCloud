package com.example.demo.controller;


import com.example.demo.common.redis.Redis1;
import com.example.demo.common.util.JsonHelper;
import com.example.demo.common.util.MapUtil;
import com.example.demo.common.util.OKHttp3Util;
import com.example.demo.common.util.RestTemplateHelper;
import com.example.demo.entity.RemitBankColor;
import com.example.demo.service.RemitBankColorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/RemitBankColorController")
public class RemitBankColorController {

    @Autowired
    private RemitBankColorService remitBankColorService;
    @Autowired
    RedisTemplate<String,String> stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Autowired
    private static RedisTemplate redisTemplate;

    @Resource
    Redis1 redis1;
    @Autowired
    RedisTemplate<String,String> edisTemplate;

    @Autowired
    RestTemplate restTemplate;
    @PostMapping(value = "/listBankColor")
    public List<RemitBankColor> listRemit(@RequestParam("typeCode") String typeCode) {
        Map<String,Object> map=new HashMap<>();
        map.put("typeCode",typeCode);
        return remitBankColorService.listRemit(map);
    }


    @PostMapping(value = "/listBankColorIn")
    public List<RemitBankColor> listBankColorIn(@RequestParam("typeCode") String typeCode,@RequestParam("a")String a,@RequestParam("b")String b) {

        List<String> list=new ArrayList<>();
        list.add(a);
        list.add(b);
        return remitBankColorService.listRemit(typeCode,list);
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
    public String getFeign(){
        Map<String,String> map=new HashMap<>();
        map.put("name","name");
        String name="name";
        ResponseEntity<String> responseEntity =restTemplate.postForEntity("http://service-feign/RemitBankColorController/getService1",name,String.class);
        String a=responseEntity.getBody();
        return  a;
    }


    @PostMapping(value = "/getFeign6")
    public String getFeign6(){
        Map<String,String> map=new HashMap<>();
        map.put("name","name");
        String name="name";

        String a=getPostForm("http://service-feign/RemitBankColorController/getService1",map);
        return  a;
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

    public  String getForm(String url, Map<String, String> map)  {
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




    public  String getPostForm(String url, Map<String, String> map)  {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        if (MapUtils.isNotEmpty(map)) {
            map.forEach(requestEntity::set);
        }
        return  restTemplate.postForObject(url, requestEntity,String.class);

    }


    @PostMapping(value = "/getFeign1")
    public String getFeign1(){
        Map<String,String> map=new HashMap<>();
        map.put("name","name");

        String  a= restTemplate.getForObject("http://service-feign/RemitBankColorController/getService1",String.class,JsonHelper.parseToJson(map));
        return  a;
    }

    @PostMapping(value = "/getFeign2")
    public String getFeign2(){
        String name ="ss";
        String a= restTemplate.getForObject("http://service-feign/RemitBankColorController/getService1?name="+name,String.class);
        return  a;
    }


    @PostMapping(value = "/getList")
    public Map<String, Object> getList(String typeCode) {

        if (stringRedisTemplate.hasKey("aaa11a11")) {
            Map<Object, Object> resultMap = stringRedisTemplate.opsForHash().entries("aaa11a11");
            String a=JsonHelper.parseToJson(resultMap);
            return JsonHelper.parseToMap(a);
        } else {
            Map<String, Object> map = remitBankColorService.list(typeCode);
            Map<String, String> mapa=new HashMap<>();
            mapa.put("s","s");
            stringRedisTemplate.opsForHash().putAll("aaa11a11", mapa);
            return map;
        }
    }




    @PostMapping(value = "/getList1")
    public Map<String, Object> getList1(String typeCode) {

        if (redis1.exists("11")) {
            Map<Object, Object> resultMap = (Map<Object, Object>) redis1.get("11");
            String a=JsonHelper.parseToJson(resultMap);
            return JsonHelper.parseToMap(a);
        } else {
            Map<String, Object> map = remitBankColorService.list(typeCode);
           // template.opsForHash().putAll("myCache",map);
            redis1.save("11",map,111);

            return map;
        }
    }

    @PostMapping(value = "/getList2")
    public List getList2(String typeCode) {

        if (redis1.exists("11")) {
            return (List) redis1.get("11");
        } else {
            Map<String, Object> map = remitBankColorService.list(typeCode);
            // template.opsForHash().putAll("myCache",map);
            List list=new ArrayList();
            list.add(map.get("bankName"));
            list.add(1);
            list.add(1);
            list.add(1);
            redis1.save("11",list,111);
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
            if (MapUtils.getString(map,"bankName")==null){
                map.put("bankName","sss");
            }

            redis1.save("111",map.get("bankName"),111);
            return (String) map.get("bankName");
        }
    }

}
