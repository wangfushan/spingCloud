package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class SendTxSelect {
    //xSelect 事务回滚模式
public static final  String QUEUE_NAME="test_name_txSelect";
    public static void main(String[] args) throws IOException, TimeoutException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg ="hellow tx select";

       try {
           //重点 开启事务
           channel.txSelect();
           //发送消息
           channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

           //模拟异常
           int a=1/0;

            System.out.println("send OK");
           // 正常发送没有异常的话 直接提交事务
           channel.txCommit();
       }catch (Exception e){

           //如果出现异常 则直接回滚
           channel.txRollback();
           System.out.println("发送异常 触发回滚");

       }
        //关闭服务
        channel.close();

        connection.close();

    }

}
