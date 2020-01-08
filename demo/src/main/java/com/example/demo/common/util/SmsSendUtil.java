package com.example.demo.common.util;

import com.example.demo.common.vo.SmsSandConfig;
import com.example.demo.common.vo.SmsSandReturnPo;
import com.example.demo.common.vo.SmsSandSendPo;
import com.example.demo.persitence.Enum.SmsSandStatusEnum;
import com.example.demo.service.SmsSandService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.*;
//import com.roncoo.pay.common.core.config.SystemConfig;
//import com.roncoo.pay.common.core.enums.SmsSandStatusEnum;
// SUCCESS("0000成功"),
//    PATAMETER_EMPTY("0001参数为空"),
//    SYSTEM_FAILED("0002系统异常"),
//    DOT_SUPPORT("0004不支持的手机号段"),
//    NETWORK_FAILED("0099网络异常");
//
//    /**
//     * 描述
//     */
//    private String desc;
//import com.roncoo.pay.sms.entity.SmsSandConfig;
//    private String transUrl;//URL请求地址
//    private String privateKey;//消息私钥
//    private String publicKey;//消息公钥
//    private String channelId;//渠道名称
//import com.roncoo.pay.sms.entity.SmsSandReturnPo;
//   /* 返回码 */
//    private SmsSandStatusEnum status;
//    /* 返回信息 */
//    private String message;
//import com.roncoo.pay.sms.entity.SmsSandSendPo;
// /** 手机号 **/
//    private String mobile;
//
//    /** 消息内容 **/
//    private String message;
//
//    public SmsSandSendPo(String mobile, String message) {
//        this.mobile = mobile;
//        this.message = message;
//    }
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

@Service("smsSandService")
//短信发送util
public class SmsSendUtil implements SmsSandService {
    private static final Logger LOG = LogManager.getLogger(SmsSendUtil.class);

    public SmsSandReturnPo sendSms(SmsSandSendPo smsPo) {
        //先把返回状态给写死
        LOG.info("消息发送参数-" + smsPo.toString());
        SmsSandReturnPo SmsReturn = new SmsSandReturnPo();
        SmsReturn.setStatus(SmsSandStatusEnum.NETWORK_FAILED);//NETWORK_FAILED
        SmsReturn.setMessage(SmsSandStatusEnum.NETWORK_FAILED.getDesc());//0099网络异常
        //SmsSandConfig对象是配置对象 也就4个字段 把传得东西参数封装一起
        SmsSandConfig config = new SmsSandConfig();
        //
    ;

        config.setChannelId("000009");
        config.setTransUrl("http://172.28.250.243:18081/ibs-web/sms/sendSms");
        config.setPrivateKey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALjg7ZC2Md/OJtDmplTkZJVSynDYi/KXb8BkjZ3HyywF7SMZfDpJKgMdMO/AIG30vTfEMVcsFjfi968KH9GmKVnwIfSRWEyfSPJFGPynKILOplcOTGsv6itxq9hdAgJKrMxXG20WB38+6eP67WDwSjVJteyu1VUK460OHs5DJk+9AgMBAAECgYEAozU1o52js75qt9oWFjU1sjjPLAWOrg8QAVkDR7l725YzvNuftlimdO9NJymFt2vpqpRRNnJd/uIn42ddpZc5QoL7LJ05GELyAMUPZItsvDbkb/dMrhYPe7uQM36MJsm26T/Tj/WjyRX/gvwCqqh+y2EPYvyV2nucCOZYaVGeL8UCQQDiL8GkMGqz7nsiAHGAlSlVybb9CWlk0LQpJK5xtHayMy5Ce4eQvheeg2wQkCQuD4GzNlnZVH+bX3Ggs05fBlgPAkEA0T9QC2stj+P0q4JO5fMHnStW8qb0NK2taZByY/qoTNwsJoqqxDs/GIlwKSwR3060843X8QbM46B6aLyvEr4vcwJBAMbQf39Y55G9QBKFHT+O3USBCA2nJjLtmE7J5WQg4myEvFbHykOaM5yrnGN6r7KlAraKQbPcqEXkQTamJW81XlUCQHaXMm92S/lMEcQOc1bh+UO7Z2jY9HjSogC9TAeb7Ty6YA9r8cKLbYQjiLzc7DgqbGXI00UYPJEZx6aKoZK21T8CQHMzF+nj0ep3KUKy2a2nJtmGILQvlj/IcIGgtVqsa715fdxvURzTWpErWRVslpaqf/bJrtnrtVT/ka9RVGDce9I=");
        config.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtTHQO/LrVhQnC9oycspg2yj5aVVNkkl6APMF9FTY63ameCySY75N50pigUgnVEFbBL6ILa/NGeY5TgWvf3EMmLX+vDFB53ZPHniK3B/v1OVIUp3CaGS/NvvYTRRGwOw+2dm9uJITdTaI6HWZYuscoTMpLog4Bkk4V2CIlJhrcyQIDAQAB");

 //       config.setChannelId(SystemConfig.SAND_SMS_CHANNEL_ID);//000009  (短信渠道号)
   //     config.setTransUrl(SystemConfig.SAND_SMS_TRANS_URL);//http://172.28.250.243:18081/ibs-web/sms/sendSms （后台服务网址）
     //   config.setPrivateKey(SystemConfig.SAND_SMS_PRIVATE_KEY);//  （#验证私钥） MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALjg7ZC2Md/OJtDmplTkZJVSynDYi/KXb8BkjZ3HyywF7SMZfDpJKgMdMO/AIG30vTfEMVcsFjfi968KH9GmKVnwIfSRWEyfSPJFGPynKILOplcOTGsv6itxq9hdAgJKrMxXG20WB38+6eP67WDwSjVJteyu1VUK460OHs5DJk+9AgMBAAECgYEAozU1o52js75qt9oWFjU1sjjPLAWOrg8QAVkDR7l725YzvNuftlimdO9NJymFt2vpqpRRNnJd/uIn42ddpZc5QoL7LJ05GELyAMUPZItsvDbkb/dMrhYPe7uQM36MJsm26T/Tj/WjyRX/gvwCqqh+y2EPYvyV2nucCOZYaVGeL8UCQQDiL8GkMGqz7nsiAHGAlSlVybb9CWlk0LQpJK5xtHayMy5Ce4eQvheeg2wQkCQuD4GzNlnZVH+bX3Ggs05fBlgPAkEA0T9QC2stj+P0q4JO5fMHnStW8qb0NK2taZByY/qoTNwsJoqqxDs/GIlwKSwR3060843X8QbM46B6aLyvEr4vcwJBAMbQf39Y55G9QBKFHT+O3USBCA2nJjLtmE7J5WQg4myEvFbHykOaM5yrnGN6r7KlAraKQbPcqEXkQTamJW81XlUCQHaXMm92S/lMEcQOc1bh+UO7Z2jY9HjSogC9TAeb7Ty6YA9r8cKLbYQjiLzc7DgqbGXI00UYPJEZx6aKoZK21T8CQHMzF+nj0ep3KUKy2a2nJtmGILQvlj/IcIGgtVqsa715fdxvURzTWpErWRVslpaqf/bJrtnrtVT/ka9RVGDce9I=
       // config.setPublicKey(SystemConfig.SAND_SMS_PUBLIC_KEY);//#验证公钥）    MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtTHQO/LrVhQnC9oycspg2yj5aVVNkkl6APMF9FTY63ameCySY75N50pigUgnVEFbBL6ILa/NGeY5TgWvf3EMmLX+vDFB53ZPHniK3B/v1OVIUp3CaGS/NvvYTRRGwOw+2dm9uJITdTaI6HWZYuscoTMpLog4Bkk4V2CIlJhrcyQIDAQAB


        Map<String, String> data = new HashMap<>();
        Map<String, String> rspData;

//f封装传输协议参数及信息内容和手机号
        data.put("version", "ver1.0.0");
        data.put("charset", "UTF-8");
        data.put("phone", smsPo.getMobile());
        data.put("message", smsPo.getMessage());
        data.put("channelId", config.getChannelId());
        data.put("signType", "RSA");
        Integer sendCount = 0;
        while (sendCount < 3) {
            try {
                ++sendCount;
                //rspData = tradeSend(data, config);

                String url = config.getTransUrl();
                Map<String, String> map = this.fillRequestData(data, config);
                String resp = this.post(map, url, "UTF-8");
                //this.processResponse(resp, config);
                //JSONObject respjSON = JSONObject.fromObject(respStr);
                rspData = JsontoMap(resp);

                LOG.debug("验证签名成功");
                String returnStatus = rspData.getOrDefault("status", "");
                String returnMsg = rspData.getOrDefault("msg", "");
                //String refundStatus=rspData.getOrDefault("refund_status");

                LOG.info("消息成功状态:returnStatus:{} returnMsg:{}"
                    , returnStatus, returnMsg);
                if ("0000".equals(returnStatus)) {
                    SmsReturn.setStatus(SmsSandStatusEnum.SUCCESS);
                    SmsReturn.setMessage(SmsSandStatusEnum.SUCCESS.getDesc());
                } else if ("0001".equals(returnStatus)) {
                    SmsReturn.setStatus(SmsSandStatusEnum.PATAMETER_EMPTY);
                    SmsReturn.setMessage(SmsSandStatusEnum.PATAMETER_EMPTY.getDesc());
                } else if ("0002".equals(returnStatus)) {
                    SmsReturn.setStatus(SmsSandStatusEnum.SYSTEM_FAILED);
                    SmsReturn.setMessage(SmsSandStatusEnum.SYSTEM_FAILED.getDesc());
                } else if ("0004".equals(returnStatus)) {
                    SmsReturn.setStatus(SmsSandStatusEnum.DOT_SUPPORT);
                    SmsReturn.setMessage(SmsSandStatusEnum.DOT_SUPPORT.getDesc());
                }
                SmsReturn.setMessage(returnMsg);
                //0000：成功；0001：参数为空；0002：系统异常;0004不支持的手机号段

                break;
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error(e.getMessage() + "信息发送网络异常，次数__" + sendCount);
            }
        }
        LOG.info("消息发送返回：{}，调用短信接口：{}，调用次数：{}", toJson(SmsReturn), toJson(smsPo), sendCount);
        return SmsReturn;
    }


    //
    public Map<String, String> tradeSend(Map<String, String> reqData, SmsSandConfig config) throws Exception {
        String url = config.getTransUrl();
        Map<String, String> map = this.fillRequestData(reqData, config);
        String resp = this.post(map, url, "UTF-8");
        return this.processResponse(resp, config);
    }

    /**
     * 向 Map 中添加公共请求参数 <br>
     * @param reqData
     * @return
     * @throws Exception
     */
    public Map<String, String> fillRequestData(Map<String, String> reqData,SmsSandConfig config) throws Exception {

        reqData.put("sign", this.generateSignature(reqData, config.getPrivateKey()));
        return reqData;
    }

    public static String generateSignature(Map<String, String> data, String key) throws Exception {
        String stringData = getSignContent(data);
        getLogger().debug("签名内容 : " + stringData);
        return sign(stringData.getBytes("utf-8"), key);
//		return rsa256Sign(stringData, key);
    }

    /**
     * 日志
     *
     * @return
     */
    public static Logger getLogger() {
        Logger logger = LogManager.getLogger("alipay java sdk");
        return logger;
    }
    public static String post(Map<String, String> reqData,String reqUrl,String encoding) {
        Map<String, String> rspData = new HashMap<>();
        String resultStr="";
        LOG.debug("请求短信地址:" + reqUrl);
        //发送后台请求数据
        try {
            JSONObject json = JSONObject.fromObject(reqData);
            resultStr = OKHttp3Util.postJsonNoProxy(reqUrl,json.toString());
//			resultStr = OKHttp3Util.postJson(reqUrl,json.toString());
//			resultStr = OKHttp3SpecialUtil.post(reqUrl, reqData, OKHttp3SpecialUtil.TYPE_FORM);
            if (StringUtils.isNotBlank(resultStr)) {
                // 将返回结果转换为map
                Map<String,String> tmpRspData  = convertResultStringToMap(resultStr);
                rspData.putAll(tmpRspData);
            } else {
                LOG.info("返回http状态码[异常]，请检查请求报文或者请求地址是否正确");
            }
        } catch (Exception e) {
            LOG.info(e.getMessage(), e);
        }
        return resultStr;
    }


    /**
     * 将形如key=value&key=value的字符串转换为相应的Map对象
     *
     * @param result
     * @return
     */
    public static Map<String, String> convertResultStringToMap(String result) {
        Map<String, String> map = null;
        try {

            if (StringUtils.isNotBlank(result)) {
                if (result.startsWith("{") && result.endsWith("}")) {
                    System.out.println(result.length());
                    result = result.substring(1, result.length() - 1);
                }
                map = parseQString(result);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 解析应答字符串，生成应答要素
     *
     * @param str
     *            需要解析的字符串
     * @return 解析的结果map
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> parseQString(String str) throws UnsupportedEncodingException {

        Map<String, String> map = new HashMap<String, String>();
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        char curChar;
        String key = null;
        boolean isKey = true;
        boolean isOpen = false;// 值里有嵌套
        char openName = 0;
        if (len > 0) {
            for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
                curChar = str.charAt(i);// 取当前字符
                if (isKey) {// 如果当前生成的是key

                    if (curChar == '=') {// 如果读取到=分隔符
                        key = temp.toString();
                        temp.setLength(0);
                        isKey = false;
                    } else {
                        temp.append(curChar);
                    }
                } else {// 如果当前生成的是value
                    if (isOpen) {
                        if (curChar == openName) {
                            isOpen = false;
                        }

                    } else {// 如果没开启嵌套
                        if (curChar == '{') {// 如果碰到，就开启嵌套
                            isOpen = true;
                            openName = '}';
                        }
                        if (curChar == '[') {
                            isOpen = true;
                            openName = ']';
                        }
                    }
                    if (curChar == '&' && !isOpen) {// 如果读取到&分割符,同时这个分割符不是值域，这时将map里添加
                        putKeyValueToMap(temp, isKey, key, map);
                        temp.setLength(0);
                        isKey = true;
                    } else {
                        temp.append(curChar);
                    }
                }

            }
            putKeyValueToMap(temp, isKey, key, map);
        }
        return map;
    }

    private static void putKeyValueToMap(StringBuilder temp, boolean isKey, String key, Map<String, String> map)
            throws UnsupportedEncodingException {
        if (isKey) {
            key = temp.toString();
            if (key.length() == 0) {
                throw new RuntimeException("QString format illegal");
            }
            map.put(key, "");
        } else {
            if (key.length() == 0) {
                throw new RuntimeException("QString format illegal");
            }
            map.put(key, temp.toString());
        }
    }

    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     *
     * @param respStr
     *            API返回的相应的数据
     * @return Map类型数据
     * @throws Exception
     */
    public Map<String, String> processResponse(String respStr,SmsSandConfig config) throws Exception {
        //Map<String, Object> reqData;
        Map<String, String> reData;
        //JSONObject respjSON = JSONObject.fromObject(respStr);
        reData = JsontoMap(respStr);
        return reData;
//		String sign = respjSON.getString("sign");
//		methodName = methodName.replace(".", "_");
//		String rstStr = respjSON.getString(methodName+"_response");
//		if (isResponseSignatureValid(sign, rstStr,config)) {
//			//reqData = JacksonUtil.obj2map(rstStr);
//			reData = JsontoMap(rstStr);
//			return reData;
//		} else {
//			throw new Exception(String.format("Invalid sign value in resp: %s", rstStr));
//		}
    }

    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                ++index;
            }
        }
        String stringData = content.toString();
        return stringData;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data
     *            加密数据
     * @param privateKey
     *            Base64编码格式的私钥
     *
     * @return 经过Base64编码的字符串
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        //KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance("SHA1WithRSA");
        //Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }

    public Map<String, String> JsontoMap(String respStr) {
        Map<String, String> result = new HashMap<>();
        JSONObject jsonObject = JSONObject.fromObject(respStr);
        Iterator<String> iterator = jsonObject.keys();
        String key = null;
        String value = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decodeBase64(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.encodeBase64String(key);
    }

















    ///单独封装一个类  Jacksonutil

    private static final ObjectMapper sObjectMapper = new ObjectMapper();
    private static final XmlMapper sXmlMapper = new XmlMapper();

    static {
        sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)//忽略unknown
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)//字典序
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);//忽略null
        sXmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)//忽略unknown
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)//字典序
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);//忽略null
    }

    /**
     * java bean to json
     *
     * @param obj Object
     * @return xml string
     */
    public static String obj2json(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOG.error("object convert to json error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java bean to map
     *
     * @param obj Object
     * @return Map
     */
    public static <K, V> Map<K, V> bean2map(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, new TypeReference<Map<K, V>>() {
            });
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java obj to bean
     *
     * @param obj Object
     * @return T
     */
    public static <T> T obj2bean(Object obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, clazz);
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java bean to map
     *
     * @param obj Object
     * @return Map
     */
    public static Map<String, Object> obj2map(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java bean to map
     *
     * @param obj Object
     * @return Map
     */
    public static Map<String, String> obj2MapStr(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return sObjectMapper.readValue(json, clazz);
        } catch (Exception e) {
            LOG.error("json string convert to object error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(String json, TypeReference<T> typeReference) {
        if (json == null) {
            return null;
        }
        try {
            return sObjectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            LOG.error("json string convert to object error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(InputStream in, Class<T> clazz) {
        try {
            return sObjectMapper.readValue(in, clazz);
        } catch (IOException e) {
            LOG.error("stream convert to object error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(Reader src, Class<T> clazz) {
        try {
            return sObjectMapper.readValue(src, clazz);
        } catch (IOException e) {
            LOG.error("stream convert to object error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * json string convert to map
     */
    public static <K, V> Map<K, V> json2map(String jsonStr) {
        if (jsonStr == null) {
            return null;
        }
        try {
            return sObjectMapper.readValue(jsonStr, new TypeReference<Map<K, V>>() {
            });
        } catch (Exception e) {
            LOG.error("json string convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * json string convert to List
     */
    public static <T> List<T> json2list(String jsonStr, Class<T> clazz) {
        if (jsonStr == null) {
            return null;
        }
        try {
            //CollectionType javaType = sObjectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            //return sObjectMapper.readValue(jsonStr,javaType);
            //速度快
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + clazz.getName() + ";");
            return Arrays.asList(sObjectMapper.readValue(jsonStr, arrayClass));
        } catch (Exception e) {
            LOG.error("json string convert to map error,{}", e.getMessage());
        }
        return null;
    }

    public static String obj2Xml(Object obj) throws JsonProcessingException {
        return sXmlMapper.writeValueAsString(obj);
    }

    public static <T> T xml2obj(String xmlStr, Class<T> clazz) throws IOException {
        return sXmlMapper.readValue(xmlStr, clazz);
    }

    public static <K, V> Map<K, V> xml2Map(String xmlStr) throws IOException {
        return sXmlMapper.readValue(xmlStr, new TypeReference<Map<K, V>>() {
        });
    }


    //#########################jsonuntil
    private static Map<Class<?>, Object> SERIALIZER;
    private static Map<Class<?>, Object> DESERIALIZER;

    static {
        DESERIALIZER = new HashMap<Class<?>, Object>();
        DESERIALIZER.put(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return json == null ? null : new Date(json.getAsLong());
            }
        });
        DESERIALIZER.put(java.sql.Date.class, new JsonDeserializer<Date>() {
            @Override
            public java.sql.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return json == null ? null : new java.sql.Date(json.getAsLong());
            }
        });

        SERIALIZER = new HashMap<Class<?>, Object>();
        SERIALIZER.put(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        });
        SERIALIZER.put(java.sql.Date.class, new JsonSerializer<java.sql.Date>() {
            @Override
            public JsonElement serialize(java.sql.Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        });
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return fromJson(json, classOfT, DESERIALIZER);
    }

    public static <T> T fromJson(String json, Type type) {
        return fromJson(json, type, DESERIALIZER);
    }

    public static <T> T fromJson(String json, Type type, Map<Class<?>, Object> adapters) {
        if (json == null || json.equals("")) {
            return null;
        }
        Gson gson = getGson(adapters);
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(String json, Class<T> classOfT, Map<Class<?>, Object> adapters) {
        Gson gson = getGson(adapters);
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object jsonElement) {
        return toJson(jsonElement, SERIALIZER);
    }

    public static String toJson(Object jsonElement, Type type) {
        Gson gson = getGson(SERIALIZER);
        return gson.toJson(jsonElement, type);
    }

    public static String toJson(Object jsonElement, Map<Class<?>, Object> adapters) {
        Gson gson = getGson(adapters);
        return gson.toJson(jsonElement);
    }

    public static Gson getGson(Map<Class<?>, Object> adapters) {
        Gson gson = null;
        if (adapters != null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            for (Map.Entry<Class<?>, Object> entry : adapters.entrySet()) {
                gsonBuilder.registerTypeAdapter(entry.getKey(), entry.getValue());
            }
            gson = gsonBuilder.create();
        } else {
            gson = new GsonBuilder().create(); // .serializeNulls()
        }
        return gson;
    }

}
