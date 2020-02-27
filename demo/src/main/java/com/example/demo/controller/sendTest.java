package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class sendTest {



    private static final String QUEUE_NAME="Topic";

    private static  final  String  EXCANGE_Name="test_exchange_name1";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection= MqUtil.getConnection();


        //创建通道

        Channel channel=connection.createChannel();

        //限制每次只发1条消息

        channel.basicQos(1);

        //声明交换机
        String type="topic";
        channel.exchangeDeclare(EXCANGE_Name,type,true,false,null);

////////direct交换机模式

        //发送消息  不需要开辟通道
        String routingKey="info.add";//只有匹配到改key的通道才可以接受到信息
        channel.basicPublish(EXCANGE_Name,routingKey,null,"ssss".getBytes());

        System.out.println("send");




        /////公平分发
/*
        //声明队列
        boolean dir=true;//是否持久化  rabbitmq中消息可以持久化到erlang的数据库中
        boolean pri=false;//是否私有化  如果私有化 只能有一个消费者消费 并且当消费节连接 为0时自动删除改通道
        boolean aotudelect=false;// 当连接的消费者为0时是否自动删除该通道
        channel.queueDeclare(QUEUE_NAME,dir,pri,aotudelect,null);
*/
/*
        for (int i=1;i<=100;i++){

            //发送消息
            String msg="第****"+i+"****条消息";

            //
            String exchange="";//交换机

            channel.basicPublish(exchange,QUEUE_NAME,null,msg.getBytes());


            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("send OK *************"+i);
            }
        }*/
        channel.close();
        connection.close();



    }
}
