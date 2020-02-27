package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class SendGongPing {
    //公平分发
public static final  String QUEUE_NAME="test_workQueue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        //每个消费者发送确认消息之前 消息队列不发送下一条消息给消费者 一次只发一条消息
        //限制发送给同一消费者 不得超过一条消息
        int basicQos=1;
        channel.basicQos(basicQos);
        //循环发送
       for (int i=0;i<=100;i++){
           String mas="hello"+i;
           channel.basicPublish("",QUEUE_NAME,null,mas.getBytes());
           System.out.println(mas);
           Thread.sleep(i*20);
       }
        //关闭服务
        channel.close();

        connection.close();

    }

}
