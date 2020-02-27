package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class SendConfirmPiLiang {
    //Confirm 批量模式 事务回滚模式
public static final  String QUEUE_NAME="test_name_confirm2";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //重点  设置confirm模式 s生产者将channel设为confirm模式
        channel.confirmSelect();


      // 利用循环模拟
      //多条一起发 只做一个确认
        for (int i=1;i<=10;i++){
            String msg ="hellow tx select**********"+i;

            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }

        //根据rabbitMq broker返回的信息判断是否发送成功
        if (!channel.waitForConfirms()){
            System.out.println("发送失败！");
        }else {
            System.out.println("发送成功！");
        }

        //关闭服务
        channel.close();

        connection.close();

    }

}
