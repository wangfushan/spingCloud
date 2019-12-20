package com.example.demo.controller;


import com.example.demo.common.redis.Redis1;
import com.example.demo.common.util.JsonHelper;
import com.example.demo.common.util.MapUtil;
import com.example.demo.entity.RemitBankColor;
import com.example.demo.service.RemitBankColorService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
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
    @PostMapping(value = "/listBankColor")
    public List<RemitBankColor> listRemit(@RequestParam("typeCode") String typeCode) {
        Map<String,Object> map=new HashMap<>();
        map.put("typeCode",typeCode);
        return remitBankColorService.listRemit(map);
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
