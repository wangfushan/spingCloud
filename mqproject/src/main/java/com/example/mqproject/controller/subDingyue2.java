package com.example.mqproject.controller;

import com.example.mqproject.util.MqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者
public class subDingyue2 {
    public static final  String QUEUE_NAME="test_exchange_queue_sms";
    //定义交换机
    public static final  String EXCHEGE_NAME="test_exchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定交换机
        channel.queueBind(QUEUE_NAME,EXCHEGE_NAME,"");
        channel.basicQos(1);//保证一次只发一条消息

        //定义一个消费者
        Consumer consumer=new DefaultConsumer(channel){
            //消息到达接受方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg=new String(body,"utf-8");
                System.out.println("(2) sub"+msg);
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println("(2) done");
                    //消息处理结束后手动回执一条消息给Mq
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        };

        //监听队列
        boolean autoAcck=false;  //自动应答改为fslse  就是改为手动应答
        channel.basicConsume(QUEUE_NAME,autoAcck,consumer);


    }




}
