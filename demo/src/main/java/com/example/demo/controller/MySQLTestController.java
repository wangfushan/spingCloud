package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySQLTestController {

    @Value("${server.port}")
    String port;



    @RequestMapping("/home")
    public String toHome(@RequestParam(value = "name") String name) {
        return "hi, "+name+" i an come from"+port;
    }



    @RequestMapping("/hi")
    public String hi() {

        return "hi";
    }

}
