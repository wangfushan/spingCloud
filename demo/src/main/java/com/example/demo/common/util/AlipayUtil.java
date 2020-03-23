package com.example.demo.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AlipayUtil {


    //RSA 数据签名加密
    public static String generateSignature(Map<String, String> data, String key) throws Exception {
        String stringData = getSignContent(data);

        return rsa256Sign(stringData, key);
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

    protected static String rsa256Sign(String content, String key) throws Exception {
        try {

            PrivateKey priKey = restorePrivateKey(Base64.decodeBase64(key));
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes("utf-8"));
            byte[] signed = signature.sign();
            String sign = new String(base64Encode(signed),"UTF-8");

            return sign;
        } catch (Exception e) {
            throw new RuntimeException("RSAcontent = " + content + "; charset = " + "utf-8", e);
        }
    }


    /**
     * BASE64编码
     *
     * @param inputByte
     *            待编码数据
     * @return 解码后的数据
     * @throws IOException
     */
    public static byte[] base64Encode(byte[] inputByte) throws IOException {
        return Base64.encodeBase64(inputByte);
    }
    /**
     * 还原私钥
     *
     * @param keyBytes
     * @return
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
