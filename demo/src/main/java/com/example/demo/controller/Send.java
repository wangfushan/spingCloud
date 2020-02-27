package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Send {
public static final  String QUEUE_NAME="test_name";
    public static void main(String[] args) throws IOException, TimeoutException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg="hell baoluo";
        //发送消息
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        System.out.println(msg);


        //关闭服务
        channel.close();

        connection.close();

    }

}
