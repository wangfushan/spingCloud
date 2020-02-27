package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class SendDingyue {
    //订阅模式
//定义交换机
public static final  String EXCHEGE_NAME="test_exchange";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHEGE_NAME,"fanout");//分发
        //消息
        String msg="hellw exchange";
        //发送消息到交换机
        channel.basicPublish(EXCHEGE_NAME,"",null,msg.getBytes());

        System.out.println(msg+"send");

        //关闭服务
        channel.close();

        connection.close();

    }

}
