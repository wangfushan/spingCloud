package com.example.feign.entity;

import lombok.Data;

@Data
public class DataBean {


    private int noticeId;
    private String noticeTitle;
    private Object noticeImg;
    private long noticeCreateTime;
    private long noticeUpdateTime;
    private String noticeContent;
}
