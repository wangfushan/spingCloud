package com.example.demo.common.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqUtil {
//获取连接
    public static Connection getConnection() throws IOException, TimeoutException {
//定义一个连接工厂
        ConnectionFactory factory=new ConnectionFactory();
//设置服务地址
        factory.setHost("127.0.0.1");
//AMQP 类似端口号
        factory.setPort(5672);
//vhost  类似mysql的db
        factory.setVirtualHost("/vhost_mq");
//用户名
        factory.setUsername("user_mq");
//密码
        factory.setPassword("123");

//返回一个连接工厂对象
        return factory.newConnection();


    }



}
