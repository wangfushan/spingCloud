package com.example.demo.service;

import com.example.demo.common.annotation.AspectColor;
import com.example.demo.common.util.RestTemplateHelper;
import com.example.demo.common.util.RestTemplateHelperV2;
import com.example.demo.entity.RemitBankColor;
import com.example.demo.mapper.RemitBankColorMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RemitBankColorService {
    @Autowired
    private RemitBankColorMapper remitBankColorMapper;

    @Autowired
    RestTemplate restTemplate;
    @AspectColor
    public List<RemitBankColor> listRemit(Map<String, Object> map) {

        return remitBankColorMapper.listBankColor(map);
    }


    public List<RemitBankColor> listRemit(String typeCode,List <String>list) {

        return remitBankColorMapper.listBankColorIn(typeCode,list);
    }

    public Map<String, Object> list(String typeCode) {
        Example example = new Example(RemitBankColor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeCode", typeCode);
        List<RemitBankColor> list = remitBankColorMapper.selectByExample(example);
        Map<String, Object> map = new HashMap<>();
        if (list.isEmpty()) {
            map.put("a", null);
        } else {
            map.put("a", list);
        }
        return map;
    }

    public void saveList(List list){
         remitBankColorMapper.saveList(list) ;
    }


    @HystrixCommand(fallbackMethod = "demoError")
    public String getHystrix(String name){
        Map<String,String> map=new HashMap<>();
        map.put("name","name");
        String a=restTemplate.postForObject("http://service-feign/RemitBankColorController/getService1", RestTemplateHelperV2.postForm(map),String.class);
        return a;
    }

    public String demoError(String name){
        return "hi"+name+"Sorry,error";
    }



}
