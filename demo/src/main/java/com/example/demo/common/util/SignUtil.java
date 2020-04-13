package com.example.demo.common.util;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.demo.common.config.BaseJsonBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class SignUtil {
    /**
     *
     * @param params
     * @param privateKey
     *                    ********样板****
     * @param charset
     * @return
     */
   // boolean signCheckResult = SignUtil.check(gatewayRequestBody, userInfo.getMerchantPublicKey(), "utf8", "RSA");

    public static String sign(Map<String, String> params, String privateKey, String charset) {
        try {
            return AlipaySignature.rsaSign(params, privateKey, charset);
        } catch (AlipayApiException e) {
            log.error("生成响应报文签名错误", e);
            return null;
        }
    }

    public static String sign(String content, String privateKey, String charset) {
        try {
            Map<String, String> params = new ObjectMapper().readValue(content, Map.class);
            return sign(params, privateKey, charset);
        } catch (IOException e) {
            log.error("生成响应报文签名错误", e);
            return null;
        }
    }

    //加密
    public static String sign(BaseJsonBean obj, String privateKey, String charset) {
        Map<String, String> params = new ObjectMapper().convertValue(obj, Map.class);
        return sign(params, privateKey, charset);
    }

    //加密  signType=RSA
    public static boolean check(BaseJsonBean obj, String publicKey, String charset, String signType) {
        Map<String, String> params = new ObjectMapper().convertValue(obj, Map.class);
        return check(params, publicKey, charset, signType);
    }

    public static boolean check(String content, String publicKey, String charset, String signType) {
        try {
            Map<String, String> params = new ObjectMapper().readValue(content, Map.class);
            return check(params, publicKey, charset, signType);
        } catch (IOException e) {
            log.error("签名数据格式转换错误", e);
            return false;
        }
    }

    public static boolean check(Map<String, String> params, String publicKey, String charset, String signType) {
        try {
            return AlipaySignature.rsaCheckV2(params, publicKey, charset, signType);
        } catch (AlipayApiException e) {
            log.error("签名验证错误", e);
            return false;
        }
    }

}
