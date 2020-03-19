package com.example.demo.easyMQ;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test_queue1") /*消费者需要监听 并声明监听的队列名称 以便获取消息*/
@Slf4j
public class consumer {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
        log.info("easyMQ接受消息"+hello+"成功");
    }
}
