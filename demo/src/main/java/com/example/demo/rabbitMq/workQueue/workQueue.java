package com.example.demo.rabbitMq.workQueue;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
@Log4j2
public class WorkQueue {


    @Autowired
    private JmsTemplate template;

    @Autowired
    private AmqpTemplate rtemplate;

/*    public String send(String hello) {
        template.setDefaultDestinationName("test_queue2");
        template.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(hello);
            }
        });

        return null;
    }*/

    public String send(String i) {
        //work队列的情况下 routinkey即为Q名   跟简单队列没有什么特别大的区别 主要在于他有两个消费者 所以 两个消费者会分摊生产者发送的多条信息
        rtemplate.convertAndSend("test_queue4",i);
        return "sss";

    }

}
