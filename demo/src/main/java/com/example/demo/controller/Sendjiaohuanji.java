package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Sendjiaohuanji {
    //订阅模式
//定义交换机  rountingKey  direct模式
public static final  String EXCHEGE_NAME="test_exchange_direct";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHEGE_NAME,"direct");//路由模式
        //消息
        String msg="hellw exchange_direct";

        //设置routingkey
        String routingKey="info";
        //发送消息到交换机
        channel.basicPublish(EXCHEGE_NAME,routingKey,null,msg.getBytes());

        System.out.println(msg+"send");

        //关闭服务
        channel.close();

        connection.close();

    }

}
