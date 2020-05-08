package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

@RestController()
public class dingshiqi {


    @RequestMapping("tradeCreateAuthYishan/getOderAndRecord")
    public String get(String a, String b) {
        dingshiqi2 task = new dingshiqi2();
        task.setA(a);
        task.setB(b +"“***");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println(format.format(calendar.getTime()));
        //calendar.add(Calendar.SECOND,3);//获取距离当前时间3秒后的时间
        Timer timer = new Timer();
        //timer.schedule(task,calendar.getTime());
        //timer.schedule(task,calendar.getTime(),2000);
        timer.schedule(task, 2000);
        System.out.println(b+"结束");
        return b+"结束";
    }




    /*   public static void main(String[] args) {
     *//**
     * Runnable：实现了Runnable接口，jdk就知道这个类是一个线程
     *//*
        Runnable runnable = new Runnable() {
            //创建 run 方法
            public void run() {
                // task to run goes here
                System.out.println("Hello, stranger");


            }
        };
        // ScheduledExecutorService:是从Java SE5的java.util.concurrent里，
        // 做为并发工具类被引进的，这是最理想的定时任务实现方式。
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        // 10：秒   5：秒
        // 第一次执行的时间为10秒，然后每隔五秒执行一次
        service.scheduleAtFixedRate(runnable, 1, 2, TimeUnit.SECONDS);

    }*/
}
