package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者
public class sub {
    public static final  String QUEUE_NAME="test_name";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

       DefaultConsumer defaultConsumer= new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg=new String(body,"utf-8");
                System.out.println(msg);
            }
        };
        //监听队列
       channel.basicConsume(QUEUE_NAME,true,defaultConsumer);

    }



}
