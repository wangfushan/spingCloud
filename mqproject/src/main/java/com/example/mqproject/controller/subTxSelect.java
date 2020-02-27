package com.example.mqproject.controller;

import com.example.mqproject.util.MqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者
public class subTxSelect {
    //xSelect 事务回滚模式
    public static final  String QUEUE_NAME="test_name_txSelect";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //定义消费者 第一种方式
        boolean autoAck=true;//是否自动应答
        channel.basicConsume(QUEUE_NAME,true,new DefaultConsumer(channel){
           //接收信息


            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println(new String(body,"utf-8"));
            }
        });

    /*    //定义消费者 第二种方式
        DefaultConsumer defaultConsumer= new DefaultConsumer(channel){
            //获取到达消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg=new String(body,"utf-8");
                System.out.println(msg);
            }
        };
        //监听队列
        boolean autoAck=true;//是否自动回复
        channel.basicConsume(QUEUE_NAME,autoAck,defaultConsumer);*/


    }



}
