package com.example.demo.service;


import com.example.demo.common.vo.SmsSandReturnPo;
import com.example.demo.common.vo.SmsSandSendPo;

public interface SmsSandService {
    public SmsSandReturnPo sendSms(SmsSandSendPo smsPo);
}
