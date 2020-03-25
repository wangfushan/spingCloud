package com.example.demo.service;

import com.example.demo.common.annotation.AspectColor;
import com.example.demo.common.util.RestTemplateHelper;
import com.example.demo.common.util.RestTemplateHelperV2;
import com.example.demo.common.util.ThreadExecutor;
import com.example.demo.entity.RemitBankColor;
import com.example.demo.mapper.RemitBankColorMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Thread.sleep;

@Slf4j
@Service
public class RemitBankColorService {
    @Autowired
    private RemitBankColorMapper remitBankColorMapper;

    @Autowired
    RestTemplate restTemplate;
    @AspectColor
    public List<RemitBankColor> listRemit(Map<String, Object> map) {

        return remitBankColorMapper.listBankColor(map);
    }

    static Object ob = "aa";//同步锁


    static int tick=0;
    public void runv1() {

        ThreadExecutor th = ThreadExecutor.create();
        th.run(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<>();
                map.put("typeCode", "102");
                 tick = remitBankColorMapper.listBankColor(map).size();//保证票数一致
                System.out.println(tick);
                Random random = new Random();//随机数种子为系统时间
                int bb=random.nextInt();
                while (tick > 0) {
                    synchronized (ob) {
                        if (tick > 0) {
                            System.out.println("卖出第" + tick + "张票！"+bb);
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


    public List<RemitBankColor> listRemit(String typeCode,List <String>list) {

        return remitBankColorMapper.listBankColorIn(typeCode,list);
    }

    public Map<String, Object> list(String typeCode) {
        Example example = new Example(RemitBankColor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeCode", typeCode);
        List<RemitBankColor> list = remitBankColorMapper.selectByExample(example);
        Map<String, Object> map = new HashMap<>();
        if (list.isEmpty()) {
            map.put("a", null);
        } else {
            map.put("a", list);
        }
        return map;
    }

    public int getList(){
        Example example = new Example(RemitBankColor.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("typeCode","102");
        int a =1;
         a=remitBankColorMapper.selectCountByExample(example);
        return a;

    }

    public void saveList(List list){
         remitBankColorMapper.saveList(list) ;
    }


    @HystrixCommand(fallbackMethod = "demoError")
    public String getHystrix(String name){
        Map<String,String> map=new HashMap<>();
        map.put("name","name");
        String a=restTemplate.postForObject("http://service-feign/RemitBankColorController/getService1", RestTemplateHelperV2.postForm(map),String.class);
        return a;
    }

    public String demoError(String name){
        return "hi"+name+"Sorry,error";
    }


    @Async
    public String  aasync(Date date) throws InterruptedException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Thread.sleep(3000);
        Date date1 = new Date();
        String dateString1 = formatter.format(date1);
        return dateString+":---------:"+dateString1;
    }

    public String  aasynca(Date date) throws InterruptedException  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Thread.sleep(3000);
        Date date1 = new Date();
        String dateString1 = formatter.format(date1);
        return dateString+":---------:"+dateString1;
    }

}
