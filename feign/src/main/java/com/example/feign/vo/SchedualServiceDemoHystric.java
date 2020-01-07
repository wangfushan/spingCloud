package com.example.feign.vo;

import com.example.feign.service.FeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class SchedualServiceDemoHystric implements FeignService {


    @Override
    public String getServiceDemo(@RequestParam(value = "name") String name) {
        return "error";
    }
}
