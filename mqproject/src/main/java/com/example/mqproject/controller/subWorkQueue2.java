package com.example.mqproject.controller;

import com.example.mqproject.util.MqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者
public class subWorkQueue2 {
    public static final  String QUEUE_NAME="test_workQueue";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //定义一个消费者
        Consumer consumer=new DefaultConsumer(channel){
        //消息到达接受方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg=new String(body,"utf-8");
                System.out.println("(2) sub"+msg);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println("(2) done");
                }

            }
        };
        
        //监听队列
        boolean autoAcck=true;
        channel.basicConsume(QUEUE_NAME,autoAcck,consumer);


    }



}
