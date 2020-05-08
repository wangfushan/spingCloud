package com.example.demo.controller;

import com.example.demo.common.util.JsonHelper;
import com.example.demo.entity.Item;
import io.micrometer.core.instrument.util.JsonUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 */
public class dalayQueue {

    public static void main(String[] args) throws InterruptedException {
        Item item1 = new Item("item1", 5, TimeUnit.SECONDS);
        Item item2 = new Item("item2",10, TimeUnit.SECONDS);
        Item item3 = new Item("item3",15, TimeUnit.SECONDS);
        DelayQueue<Item> queue = new DelayQueue<>();
        queue.put(item1);
        queue.put(item2);
        queue.put(item3);
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        for (int i = 0; i < queue.size(); i++) {
            Item take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n",take.getName(), LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }
}
