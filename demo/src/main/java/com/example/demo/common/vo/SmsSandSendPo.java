package com.example.demo.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsSandSendPo {
    /** 手机号 **/
    private String mobile;

    /** 消息内容 **/
    private String message;

    public SmsSandSendPo(String mobile, String message) {
        this.mobile = mobile;
        this.message = message;
    }
}
