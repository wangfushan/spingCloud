package com.example.demo.service;

import com.example.demo.common.util.ThreadExecutor;
import com.example.demo.mapper.RemitBankColorMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ThreadServiceOne {

    @Autowired
    private RemitBankColorMapper remitBankColorMapper;


    static Object ob = "aa";//同步锁


    public void runv1() {

        ThreadExecutor th = ThreadExecutor.create();
        th.run(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<>();
                map.put("typeCode", "102");
                int tick = remitBankColorMapper.listBankColor(map).size();//保证票数一致
                System.out.println(tick);
                while (tick > 0) {
                    Random random = new Random();//随机数种子为系统时间
                    synchronized (ob) {
                        if (tick > 0) {
                            System.out.println("卖出第" + tick + "张票！"+random.nextInt());
                            tick--;
                        } else {
                            System.out.println("票卖完了！");
                        }
                    }
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


}
