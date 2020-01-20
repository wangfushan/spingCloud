package com.example.demo.controller;


import com.example.demo.service.ThreadServiceOne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("/ThreadController")
public class ThreadController {


    @PostMapping(value = "/aa")
    public void aa(){

        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Random random = new Random();//随机数种子为系统时间
        Random random1 = new Random(10);//随机数种子为10
        ThreadServiceOne aaa=new ThreadServiceOne();
        aaa.runv1();
    }


}
