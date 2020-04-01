package com.example.demo.controller;


import com.example.demo.rabbitMq.easyMQ.publisher;
import com.example.demo.rabbitMq.workQueue.workQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/consumerController")
@Slf4j
public class ConsumerController {


    @Autowired
    private publisher publisher;
    @Autowired
    private workQueue worPpublisher;


    @PostMapping(value = "/aaa")
    public String cs(String hello) {
        publisher.send();
        log.info("ssssssssss");
        return hello;

    }

    @PostMapping(value = "/aaass")
    public String csss() {
        for (int i=1;i<=10;i++){
            String  date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); //获取当前时间
            String  Message ="Hello    "+i+"    "+date;
            worPpublisher.send(Message);
            log.info("ssssssssss");
        }
        return "ss";
    }

    @PostMapping(value = "/aaas")
    public String css(String hello) {
        publisher.send();
        log.info("ssssssssss");
        return hello;
    }
}
