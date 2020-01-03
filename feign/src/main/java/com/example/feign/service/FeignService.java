package com.example.feign.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "service-demo3", fallback = Exception.class)
public interface FeignService {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    String getServiceDemo(@RequestParam(value = "name") String name);

}
