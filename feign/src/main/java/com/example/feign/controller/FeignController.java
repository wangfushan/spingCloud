package com.example.feign.controller;

import com.example.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RemitBankColorController")
public class FeignController {


    @Autowired
    public FeignService feignService;


    @RequestMapping(value = "getService",method = RequestMethod.GET)
    public String getService(@RequestParam(value = "name") String name){
        return feignService.getServiceDemo(name);
    }



}
