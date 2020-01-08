package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.util.SmsSendUtil;
import com.example.demo.common.vo.SmsSandReturnPo;
import com.example.demo.common.vo.SmsSandSendPo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 发送短信
 */
public class TSmsSandService implements Runnable {
    private static final Logger LOG = LogManager.getLogger(TSmsSandService.class);
    private SmsSandSendPo smsSandSendPo;

    public TSmsSandService(SmsSandSendPo smsSandSendPo) {
        this.smsSandSendPo = smsSandSendPo;
    }

    @Override
    public void run() {
        SmsSandService service = new SmsSendUtil();
        SmsSandReturnPo returnPo=service.sendSms(smsSandSendPo);
        LOG.info("调用结果：{}", JSON.toJSON(returnPo));
    }
}
