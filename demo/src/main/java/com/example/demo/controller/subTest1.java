package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class subTest1 {

    private static final String QUEUE_NAME="Topic2";
    private static  final  String  EXCANGE_Name="test_exchange_name1";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= MqUtil.getConnection();
        //创建通道
        Channel channel=connection.createChannel();
        //限制每次只发1条消息
        channel.basicQos(1);

        //声明队列
        boolean dir=true;//是否持久化  rabbitmq中消息可以持久化到erlang的数据库中
        boolean pri=false;//是否私有化  如果私有化 只能有一个消费者消费 并且当消费节连接 为0时自动删除改通道
        boolean aotudelect=false;// 当连接的消费者为0时是否自动删除该通道
        channel.queueDeclare(QUEUE_NAME,dir,pri,aotudelect,null);

        channel.queueBind(QUEUE_NAME,EXCANGE_Name,"info.#");
        //创建消费者
        Consumer c=new DefaultConsumer(channel){
          //重写 handle方法接受信息

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg=new String(body,"utf-8");
                System.out.println(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("subPublish 2oK********"+msg);
                    //处理结束发给mq一条消息
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }

        };
        //监听队列
        boolean autoAck=false;//是否自动应答

        channel.basicConsume(QUEUE_NAME,autoAck,c);


    }
}
