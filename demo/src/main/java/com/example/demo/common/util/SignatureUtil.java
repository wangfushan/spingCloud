package com.example.demo.common.util;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignatureUtil {
    /**
     * 公钥加密
     *
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static String rsaEncrypt(String content, String publicKey, String charset) throws Exception {
        PublicKey pubKey = getPublicKeyFromX509(new ByteArrayInputStream(publicKey.getBytes()));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] data = (charset == null || charset.isEmpty()) ? content.getBytes() : content.getBytes(charset);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(data, offSet, 117);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 117;
        }
        byte[] encryptedData = Base.encodeBase64(out.toByteArray());
        out.close();
        return (charset == null || charset.isEmpty()) ? new String(encryptedData) : new String(encryptedData, charset);
    }


    /**
     * 私钥解密
     *
     * @param \
     * @param privateKey 私钥key
     * @param charset    编码
     * @return 返回的解密后信息
     * @throws Exception 异常
     */
    public static String rsaDecrypt(String content, String privateKey, String charset) throws Exception {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] encryptedData = (charset == null || charset.isEmpty()) ? Base.decodeBase64(content.getBytes()) : Base.decodeBase64(content.getBytes(charset));
            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密  
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > 128) {
                    cache = cipher.doFinal(encryptedData, offSet, 128);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * 128;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            return (charset == null || charset.isEmpty()) ? new String(decryptedData) : new String(decryptedData, charset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("EncodeContent = " + content + ",charset = " + charset, e);
        }
    }

    public static void main(String[] args) {
           String PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTvTrqNLGoRGAu9QPLSV0LQNZI29iPH7muVXLRYoq8VykKgk2HJ72yPeNRqoSpRTgUAKihAaXSKC3+597Pb0d3OypNCG8ibIWo5VmBEMpYZqcg8XyYT4FWFyfeu77mq12+uDQ+HKrn73HxSVyOLQgX0wJTepOpsfjs2l+zJJt8RubVPXButPzpqGUoqqV/VGULRb1csVvL0i83ipUVB/HlsQZ8Q91RFY8U6Ydao1U+cztI/R9Bfxf/xupiSmq5UpQIWzqMJ5GjLHwmwPROV1pvtLyneMhDLM1pMwyou60L/ncb9xyMfi3dnrSvoGwBiunYpc5fcM1Bu0YuCZm1XP4bAgMBAAECggEAWHFC0Kwk4oe0UMgKsmrD0ZXJ8inyFZQ3cx7iMmjchu1iu1DQGqX20Lpt2BjNfAvdC1Rtnp0+6HV3O48RenqyfLUlmKIA7cmrpjVOhI5Jo+woBvep0ABge+o2ywb3vF1ALIlXdQtyYJCeVITHbLGWk7SVQuYxebf6Pcho2/SBRD7A3bTbTuU7o5wschFYDRtfP5JdthGK8zzPirsS5CLxojHINPJ0kBnGa6qJ006pMJP93cMcYwh0DZbP8TxP1VGvnWySLwofhUBE0busJFXvEMVRVRH2rdEHH6apxk1RtaNPnBfsH4Z+664/7kcxzRrVP02Rq1vfDqNPAd2Z4fEyiQKBgQDDkUonsZF2MgV0rnDU5igAW5ZLAaeW+GN2ARgNhZbvk/U/oLsB+FJmNHbBVYSKUNMqRkEHmVuKZtgUBNW0HObM5NJmT1XCtLktaYYyiwlmJuPqqeq60bUVM4ZkTBqJBqlxonNtMxVLUrsYuIqrxjPhVN6QlD5Xxq4ckLsSETnJ5wKBgQDBZGUgtyHTgzIAksZTczvNplMF58HZT0fk2WHSOCfvjVgitqMi/SkXeKxQubgn5YUWtW6KV2KSeKx0MBQdcVoRs1wlyNcQXOcJYIzPoqBPycGOrmqTu0t1x47UHOySLZyN/ZX63+UYHNQsYCvTKm3jcdXdRk89Dfz53nnGATRrrQKBgQC+9ENa5Kfqp8bp+lYTmzNdvg/O5yuzXo8HLuREd7c78NVbrl5K+yPBxeJavlyvDWJSecyPVe+rAOZlDZ94wWuejqLd4QMVHY9eRxYQje+8faa3f/fBOHg+3lOP5TtXpliWqw9XhbR6Gqqy5aYJOXt4vnye4RDKdiWVoL/4UTeC6wKBgD8/Dxeg7K4YtOydE8FM6Vz7LSG8RphEJlYAhc5TUTQ1vII1yqpxstObQndwFXruxBTGJy2VIIaYcoXZB2sNnBA34UX2JeeTCnf1nHd3TwhpCiUYg4Bm+2m8ZsiV4QR5aD7DlR4jxO+a1gThOtPnh2Kf4vpnuRhVj8tD5/1KfZNNAoGARPwvsD06PC2SSomMJMDwAIYu28B+QJpsbiluYld19uYFFTtkUVkSWPAxwM+hNcki6OyWYom/hxZ2LIdIhUfv9/rQz7teYcOHwHWeT4gfg2h3RYN8Mwt6dSJYXS0RVTnfK/Y8gsmtS9um6gD9exDzncyqT8/ozAbOPCc5vki31BY=";
           String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk7066jSxqERgLvUDy0ldC0DWSNvYjx+5rlVy0WKKvFcpCoJNhye9sj3jUaqEqUU4FACooQGl0igt/ufez29HdzsqTQhvImyFqOVZgRDKWGanIPF8mE+BVhcn3ru+5qtdvrg0Phyq5+9x8Ulcji0IF9MCU3qTqbH47NpfsySbfEbm1T1wbrT86ahlKKqlf1RlC0W9XLFby9IvN4qVFQfx5bEGfEPdURWPFOmHWqNVPnM7SP0fQX8X/8bqYkpquVKUCFs6jCeRoyx8JsD0Tldab7S8p3jIQyzNaTMMqLutC/53G/ccjH4t3Z60r6BsAYrp2KXOX3DNQbtGLgmZtVz+GwIDAQAB";
        System.out.println("公钥：" + PUBLIC_KEY);
        System.out.println("私钥：" + PRIVATE_KEY);
        try {
            String aa=rsaEncrypt("sss",PUBLIC_KEY,"UTF-8");
            System.out.println("加密"+aa);

            String bb=rsaDecrypt(aa,PRIVATE_KEY,"UTF-8");
            System.out.println("jie密"+bb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  * RSA签名
     *  * @param content
     *  * @param privateKey
     *  * @param charset
     *  * @return
     *  * @throws Exception
     *  
     */

    public static String rsaSign(String content, String privateKey, String charset) throws Exception {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(new ByteArrayInputStream(privateKey.getBytes()));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            if (charset == null || charset.isEmpty()) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(Base.encodeBase64(signed));
        } catch (Exception e) {
            throw new Exception("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    /**
     *  * 签名校验
     *  * @param content
     *  * @param sign
     *  * @param publicKey
     *  * @param charset
     *  * @return
     *  * @throws Exception
     *  
     */

    public static boolean rsaCheckContent(String content, String sign, String publicKey, String charset) throws Exception {
        try {
            PublicKey pubKey = getPublicKeyFromX509(new ByteArrayInputStream(publicKey.getBytes()));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            if (charset == null || charset.isEmpty()) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            return signature.verify(Base.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new Exception("RSAcontent=" + content + ",sign=" + sign + ",charset=" + charset, e);
        }
    }

    /**
     *  * 获取公钥
     *  * @param algorithm
     *  * @param ins
     *  * @return
     *  * @throws Exception
     *  
     */

    public static PublicKey getPublicKeyFromX509(InputStream is) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        StringWriter writer = new StringWriter();
        Reader in = new InputStreamReader(is);
        char[] buffer = new char[4096];
        int amount;
        while ((amount = in.read(buffer)) >= 0) {
            writer.write(buffer, 0, amount);
        }
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    /**
     *  * 获取私钥
     *  * @param algorithm
     *  * @param is
     *  * @return
     *  * @throws Exception
     *  
     */

    public static PrivateKey getPrivateKeyFromPKCS8(InputStream is) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        StringWriter writer = new StringWriter();
        Reader in = new InputStreamReader(is);
        char[] buffer = new char[4096];
        int amount;
        while ((amount = in.read(buffer)) >= 0) {
            writer.write(buffer, 0, amount);
        }
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    /**
     *  * 生成密钥
     *  * @return
     *  * @throws Exception
     *  
     */

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        return keyPairGen.generateKeyPair();
    }

    /**
     *  * 根据密钥对获取公钥
     *  * @param keyPair
     *  * @return
     *  * @throws UnsupportedEncodingException 
     *  
     */

    public static String getRSAPublicKey(KeyPair keyPair, String charset) throws UnsupportedEncodingException {
        PublicKey publicKey = keyPair.getPublic();
        byte[] encodeBase64 = Base.encodeBase64(publicKey.getEncoded());
        return (charset == null || charset.isEmpty()) ? new String(encodeBase64) : new String(encodeBase64, charset);
    }

    /**
     *  * 根据密钥对获取公钥
     *  * @param keyPair
     *  * @return
     *  * @throws UnsupportedEncodingException 
     *  
     */

    public static String getRSAPrivateKey(KeyPair keyPair, String charset) throws UnsupportedEncodingException {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] encodeBase64 = Base.encodeBase64(privateKey.getEncoded());
        return (charset == null || charset.isEmpty()) ? new String(encodeBase64) : new String(encodeBase64, charset);
    }

}
