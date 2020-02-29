package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class SendTopic {
    //订阅模式
//定义交换机  rountingKey  Topic模式
public static final  String EXCHEGE_NAME="test_exchange_Topic";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHEGE_NAME,"topic");//topic模式
        //消息
        String msg="hellw exchange_Topic_商品";

        //设置routingkey 中间 。分开
        String routingKey="goods.delect";

        //String routingKey="goods.add";
        //发送消息到交换机
        //BasicProperties props -- 消息的基本属性，例如路由头等
        channel.basicPublish(EXCHEGE_NAME,routingKey,null,msg.getBytes());

        System.out.println(msg+"send");

        //关闭服务
        channel.close();

        connection.close();

    }

}
