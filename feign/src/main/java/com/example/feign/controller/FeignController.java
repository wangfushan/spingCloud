package com.example.feign.controller;

import com.example.feign.entity.Notice;
import com.example.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/RemitBankColorController")
public class FeignController {


    public static final String REUS_TEMPLATE_2 = "reusTemplate2";
    @Autowired
    public FeignService feignService;


    @Autowired
    public RestTemplate res;
    @RequestMapping(value = "getService", method = RequestMethod.GET)
    public String getService(@RequestParam(value = "name") String name) {
        return feignService.getServiceDemo(name);
    }

    @RequestMapping(value = "getService1", method = {RequestMethod.POST,RequestMethod.GET})
    public String getService1(@RequestParam(value = "name") String name) {
        String a=name;
        return a;
    }


    @RequestMapping(value = "reusTemplate", method = RequestMethod.GET)
    public String getReusTemplate(@RequestParam(value = "name") String name) {

        Notice forEntity = res.getForObject("", Notice.class);
        return "";
    }
/*
    @RequestMapping(value = "reusTemplate1", method = RequestMethod.GET)
    public String getReusTemplate1(@RequestParam(value = "name") String name) {

      *//*  HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//请求头类型
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();//因为HttpEntity接受的request类型是它。MultiValueMap是Hashmap的一个子类  支持一个key 多个value
        map.add("s","ssss");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);//restTemplate.postForEntity方法虽然表面上接收的request是@Nullable Object request类型，
           *//*
      /// 但是你追踪下去会发现，这个request是用HttpEntity来解析

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);//设置参数类型和编码


        Map<String,String> map1 = new HashMap<>();//建立简单的String，String的map
        map1.put("email", "first.last@example.com");
        map1.put("email1", "first.last@example.com1");
        map1.put("email2", "first.last@example.com2");
        HttpEntity<Map<String,String>> request1 = new HttpEntity<>(map1, headers);//包装到HttpEntity

     //  String aa = res.getForObject("http://service-demo3/demo", String.class,map);
        String aa = RestTemplateHelper.postForm("http://service-demo3/demo",map);
    */

    @RequestMapping(value = "reusTemplate11", method = RequestMethod.GET)
    public Object getReusTemplate12(@RequestParam(value = "name") String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);//设置参数类型和编码


        Map<String,String> map1 = new HashMap<>();//建立简单的String，String的map
        map1.put("name", name);
        HttpEntity<Map<String,String>> request1 = new HttpEntity<>(map1, headers);//包装到HttpEntity

        //{1} 是占位符 相当于 http://fantj.top/notice/list？name=name
        //String forEntity = res.postForObject("http://service-demo3/demo1",map1, String.class);
        ResponseEntity<String> forEntity = res.postForEntity("http://service-demo3/demo1",request1, String.class);
        return forEntity;
    }

    @RequestMapping(value = "reusTemplate112", method = RequestMethod.GET)
    public String getReusTemplate122(@RequestParam(value = "name") String name) {
        Map<String,String> map1 = new HashMap<>();//建立简单的String，String的map
        map1.put("name", name);
        String forEntity = res.postForObject("http://service-demo3/demo1",map1, String.class);
        return forEntity;
    }

    @RequestMapping(value = "reusTemplate1122", method = RequestMethod.GET)
    public String getReusTemplate1222(@RequestParam(value = "name") String name) {
        Map<String,Object> map1 = new HashMap<>();//建立简单的String，String的map
        map1.put("name", name);
        String forEntity = res.postForObject("http://service-demo3/demo1",map1, String.class);
        return forEntity;
    }



}
