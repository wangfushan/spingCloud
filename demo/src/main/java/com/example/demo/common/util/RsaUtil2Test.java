package com.example.demo.common.util;

import java.util.Map;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

//import com.byttersoft.framework.util.StringUtil;

public class RsaUtil2Test {


    private static final String SIGN_TYPE_RSA = "RSA";

    private static final String SIGN_TYPE_RSA2 = "RSA2";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    private static final int DEFAULT_BUFFER_SIZE = 8192;


    /**
     * 41      * RSA/RSA2 生成签名
     * 42      *
     * 43      * @param map
     * 44      *            包含 sign_type、privateKey、charset
     * 45      * @return
     * 46      * @throws Exception
     * 47
     */
/*    public static String rsaSign(Map map) throws Exception {
        PrivateKey priKey = null;
        java.security.Signature signature = null;
        String signType = map.get("sign_type").toString();
        String privateKey = map.get("privateKey").toString();
        String charset = map.get("charset").toString();
        String content = getSignContent(map);
        map.put("content", content);
        System.out.println("请求参数生成的字符串为:" + content);
        if (SIGN_TYPE_RSA.equals(signType)) {
            priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
        } else if (SIGN_TYPE_RSA2.equals(signType)) {
            priKey = getPrivateKeyFromPKCS8(SIGN_TYPE_RSA, new ByteArrayInputStream(privateKey.getBytes()));
            signature = java.security.Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        } else {
            throw new Exception("不是支持的签名类型 : : signType=" + signType);
        }
        signature.initSign(priKey);

        if (StringUtil.isEmpty(charset)) {
            signature.update(content.getBytes());
            signature.update(content.getBytes(charset));
        }

        byte[] signed = signature.sign();

        return new String(Base64.encodeBase64(signed));

    }*/
}
