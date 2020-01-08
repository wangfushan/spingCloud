package com.example.demo.common.vo;

public class SmsResultCode {
    /**
     * 支付宝支付常量  单独一个类
     */
    String RETURN_CODE = "code";
    String RETURN_MSG = "msg";
    String RETURN_RIGHT_CODE="10000";
    String RETURN_INPROCESS_CODE="10003";
    String RETURN_RIGHT_MSG="Success";
    String RETURN_SUB_CODE="sub_code";
    String RETURN_SUB_MSG = "sub_msg";
    String VERSION = "1.0";
    String FAIL = "Fail";
    String SUCCESS  = "success";
    String FIELD_SIGN = "sign";
    String FIELD_SIGN_TYPE = "sign_type";
    String SHA256withRSA = "SHA256withRSA";
    String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    String MD5 = "MD5";
    String KEY_ALGORITHM = "RSA";
    String CHARSET_UTF_8 = "UTF-8";
    String FORMAT = "JSON";
    boolean isIfValidateRemoteCert = false;//测试false,生产true
    String AMPERSAND = "&";
    String EQUAL = "=";
    String LEFT_BRACE = "{";
    String RIGHT_BRACE = "}";
    String indirectCreate = "ant.merchant.expand.indirect.create";
    String indirectQuery = "ant.merchant.expand.indirect.query";
    String indirectModify = "ant.merchant.expand.indirect.modify";
    String tradePrecreate = "alipay.trade.precreate";
    String tradePay = "alipay.trade.pay";
    String tradeCreate = "alipay.trade.create";
    String tradeCancel = "alipay.trade.cancel";
    String tradeClose = "alipay.trade.close";
    String tradeRefundQuery = "alipay.trade.fastpay.refund.query";
    String tradeRefund = "alipay.trade.refund";
    String tradeQuery = "alipay.trade.query";
}
