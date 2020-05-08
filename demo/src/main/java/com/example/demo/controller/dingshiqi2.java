package com.example.demo.controller;

import lombok.Data;
import org.springframework.scheduling.annotation.Async;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
@Data
@Async
public class dingshiqi2 extends TimerTask {

    String a;
    String b;

    @Override
    public void run() {
        int ietm=Integer.valueOf(a);
        System.out.println(ietm);
        if (ietm>3){
            System.out.println(b+"循环OVer");
            return;
        }
        ietm++;
        if (ietm<4){
            a=String.valueOf(ietm);
            System.out.println(b+"**********"+a);
            dingshiqi2 timerSelect = new dingshiqi2();
            timerSelect.setA(a);
            timerSelect.setB(b);
            Timer timer = new Timer();
            timer.schedule(timerSelect, 1000 );
        }else {
            System.out.println(b+"循环结束");
        }
    }

}
