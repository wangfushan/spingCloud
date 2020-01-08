package com.example.demo.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class SmsSandConfig {
    private String transUrl;//URL请求地址
    private String privateKey;//消息私钥
    private String publicKey;//消息公钥
    private String channelId;//渠道名称
}
