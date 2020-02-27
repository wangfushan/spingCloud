package com.example.demo.controller;

import com.example.demo.common.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;


public class SendConfirmYiBu {
    //Confirm 异步模式 事务回滚模式
public static final  String QUEUE_NAME="test_name_confirm3";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //拿出一个连接
        Connection connection= MqUtil.getConnection();
        //建立一个通道
        Channel channel=connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //重点  设置confirm模式 s生产者将channel设为confirm模式
        channel.confirmSelect();

        //未确认消息标识集合  有序不重复的set集合 因消息标识ID为长正型 故用Long
        final SortedSet<Long> confirmSet= Collections.synchronizedSortedSet(new TreeSet<Long>());

        //通道添加监听 重写ConfirmListener方法
        channel.addConfirmListener(new ConfirmListener() {
            //消息无异常
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple){
                    //标识返回多条信息成功无异常的确认回执
                    System.out.println("____________handleAck multiple ____________");
                    //消息无异常 从未确认标识集合中删除
                    confirmSet.headSet(deliveryTag+1).clear();
                }else {
                    //标识返回多条信息成功无异常的确认回执
                    System.out.println("____________handleAck multiple  false ____________");
                    //消息无异常 从未确认标识集合中删除
                    confirmSet.remove(deliveryTag);
                }

            }
            //消息无异常 回执消息为Nack
            @Override
            //错误有异常的消息回执  逻辑模拟成功 按照实际业务逻辑自行处理  重发 删除等等处理
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple){
                    //标识返回多条信息失败有异常的确认回执
                    System.out.println("____________handleNack multiple ____________");
                    //消息无异常 从未确认标识集合中删除
                    confirmSet.headSet(deliveryTag+1).clear();
                }else {
                    //标识返回多条信息成功无异常的确认回执
                    System.out.println("____________handleNack multiple  false ____________");
                    //消息有异常 从未确认标识集合中删除
                    confirmSet.remove(deliveryTag);
                }
            }
        });


        String msg="sssssssssssssssss";

        int i=1;
       while (i<500){
           long seqNo=channel.getNextPublishSeqNo();
           //发送无异常消息
           channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
           confirmSet.add(seqNo);
           i++;
       }

       channel.close();
       connection.close();;

    }

}
