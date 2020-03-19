package com.example.demo.easyMQ;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/*把普通pojo实例化到spring容器中*/
@Component
@Slf4j
public class publisher {/*生产者*/

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Bean
    public void send(){
        String  date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); //获取当前时间
        String  Message ="Hello"+date;
        //简单队列的情况下 routinkey即为Q名  只有一个消费者能够接受到消息
        rabbitTemplate.convertAndSend("test_queue1",Message);
        log.info("发送消息"+Message+"成功");
    }

}
