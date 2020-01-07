package com.example.feign.service;


import com.example.feign.vo.SchedualServiceDemoHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "service-demo3", fallback = SchedualServiceDemoHystric.class)
public interface FeignService {
    @RequestMapping(value = "/home")
    String getServiceDemo(@RequestParam(value = "name") String name);

}
