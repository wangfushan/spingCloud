package com.example.demo.rabbitMq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    /*普通模式和work都可用*/
    /*运行模式  生产者发送到指定队列消息  消费者使用注解进行监听队列  监听到后接收消息*/

    @Bean
    public Queue queue(){
        return new Queue("test_queue8");
    }

    @Bean
    public Queue queue2(){
        return new Queue("test_queue2");
    }

    @Bean
    public Queue queue3(){
        return new Queue("test_queue3");
    }
    @Bean
    public Queue queue4(){
        return new Queue("test_queue4");
    }

}
