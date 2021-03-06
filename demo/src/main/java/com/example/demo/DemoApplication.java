package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableConfigServer



@EnableHystrix
@EnableHystrixDashboard  /*开启断路由仪表盘*/
@EnableCircuitBreaker
@EnableAsync
public class DemoApplication {
    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Value("${server.port}")
    String port;
    @RequestMapping("/demo")
    public String getDemo(@RequestParam(name = "name")String name){
        return "端口号is："+port+"my name is"+name;
    }

    @RequestMapping("/demo1")
    public String getDemo1(@RequestBody Map map){
        return "端口号is："+port+"my name is"+map.get("name").toString();
    }

}
