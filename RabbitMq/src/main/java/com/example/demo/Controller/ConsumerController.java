package com.example.demo.Controller;


import com.example.demo.easyMQ.publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consumerController")
@Slf4j
public class ConsumerController {


    @Autowired
    private publisher publisher;


    @PostMapping(value = "/aaa")
    public String cs(String hello) {
        publisher.send();
        log.info("ssssssssss");
        return hello;
    }



    @PostMapping(value = "/aaas")
    public String css(String hello) {
        publisher.send();
        log.info("ssssssssss");
        return hello;
    }
}
