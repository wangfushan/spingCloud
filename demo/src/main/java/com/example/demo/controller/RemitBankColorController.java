package com.example.demo.controller;

import com.example.demo.common.redis.JedisRedisUtils;
import com.example.demo.entity.RemitBankColor;
import com.example.demo.service.RemitBankColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RemitBankColorController")
public class RemitBankColorController {

    @Autowired
    private RemitBankColorService remitBankColorService;
    @PostMapping(value = "/listBankColor")
    public List<RemitBankColor> listRemit(@RequestParam("typeCode") String typeCode) {
        Map<String,Object> map=new HashMap<>();
        map.put("typeCode",typeCode);
        return remitBankColorService.listRemit(map);
    }

    @PostMapping(value = "/getList")
    public Map<String, Object> getList(String typeCode) {
        if (JedisRedisUtils.exists("ss")){
            return (Map<String, Object>) JedisRedisUtils.get("ss");
        }else{
            Map<String, Object> map=remitBankColorService.list(typeCode);
            JedisRedisUtils.save("ss",map,30);
            return map;
        }


    }

}
