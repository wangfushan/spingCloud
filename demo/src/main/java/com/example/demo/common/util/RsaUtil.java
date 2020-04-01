package com.example.demo.common.util;

import com.alipay.api.internal.util.AlipaySignature;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA工具类
 *
 * @author xy
 */
public class RsaUtil {

    /**
     * 加密算法
     */
    private static final String CIPHER_DE = "RSA";
    /**
     * 解密算法
     */
    private static final String CIPHER_EN = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 私钥名称
     */
    private static final String PRIVATE_KEY_NAME = "privateKey";

    /**
     * 公钥名称
     */
    private static final String PUBLIC_KEY_NAME = "publicKey";
    /**
     * 填充
     */
    private static final String ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * 生成RSA密钥对 1024
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> buildRsa1024Key() throws Exception {
        return buildRsaKey(1024);
    }

    /**
     * 生成RSA密钥对 2048
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> buildRsa2048Key() throws Exception {
        return buildRsaKey(2048);
    }

    /**
     * 生成RSA密钥对
     *
     * @return 密钥对Map
     */
    public static Map<String, String> buildRsaKey(int length) throws Exception {

        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException var7) {
            throw new Exception("不支持的算法RSA");
        }

        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(length, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Map<String, String> keyMap = new HashMap<>();

        keyMap.put(PRIVATE_KEY_NAME, Base64.encode(privateKey.getEncoded()));
        keyMap.put(PUBLIC_KEY_NAME, Base64.encode(publicKey.getEncoded()));
        return keyMap;
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     */
    public static String achievePrivateKey(Map<String, String> keyMap) {
        return keyMap.get(PRIVATE_KEY_NAME);
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     */
    public static String achievePublicKey(Map<String, String> keyMap) {
        return keyMap.get(PUBLIC_KEY_NAME);
    }


    /**
     * RSA签名
     *
     * @param content
     * @param privateKey
     * @param charset
     * @param signType
     * @return
     */
    public static String rsaSign(String content, String privateKey, String charset, String signType) {
        try {
            return AlipaySignature.rsaSign(content, privateKey, charset, signType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * RSA签名
     *
     * @param content
     * @param privateKey
     * @param charset
     * @return
     */
    public static String rsaSign(String content, String privateKey, String charset) {
        try {
            return AlipaySignature.rsaSign(content, privateKey, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * RSA签名
     *
     * @param params
     * @param privateKey
     * @param charset
     * @return
     */
    public static String rsaSign(Map<String, String> params, String privateKey, String charset) {
        try {
            return AlipaySignature.rsaSign(params, privateKey, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean rsaCheckV1(Map<String, String> params, String publicKey,
                                     String charset) {
        try {
            return AlipaySignature.rsaCheckV1(params, publicKey, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean rsaCheckV1(Map<String, String> params, String publicKey,
                                     String charset, String signType) {
        try {
            return AlipaySignature.rsaCheckV1(params, publicKey, charset, signType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean rsaCheckV2(Map<String, String> params, String publicKey,
                                     String charset) {
        try {
            return AlipaySignature.rsaCheckV2(params, publicKey, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean rsaCheckV2(Map<String, String> params, String publicKey,
                                     String charset, String signType) {
        try {
            return AlipaySignature.rsaCheckV2(params, publicKey, charset, signType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 验签
     *
     * @param content
     * @param sign
     * @param publicKey
     * @param charset
     * @param signType  RSA RSA2
     * @return
     */
    public static boolean rsaCheck(String content, String sign, String publicKey, String charset,
                                   String signType) {
        try {
            return AlipaySignature.rsaCheck(content, sign, publicKey, charset, signType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data      源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_DE);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey    私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_EN);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    public static void main(String[] args) {

        try {
            Map<String, String> map = buildRsa2048Key();

            String privateKey = map.get("privateKey");
            String publicKey = map.get("publicKey");
            System.out.println(privateKey);
            System.out.println(publicKey);

            publicKey =
                    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCz7SbYgDmD3cZ5o9IL8BVObkHKms8I0mQ5ly6uIQm1a95J4lgcK2mdjveuVcQ5oXhC1OQx+gSe8pYgaVr1Nyse+sSg+rDOFBypr8/4i+a0OANtYXeUKzS1j7ra/lqon6loN3zYaJNLTGUponP9n6+/FHN0XEjTiW45oEzVTqcVyQIDAQAB";

            privateKey =
                    "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALPtJtiAOYPdxnmj0gvwFU5uQcqazwjSZDmXLq4hCbVr3kniWBwraZ2O965VxDmheELU5DH6BJ7yliBpWvU3Kx76xKD6sM4UHKmvz/iL5rQ4A21hd5QrNLWPutr+WqifqWg3fNhok0tMZSmic/2fr78Uc3RcSNOJbjmgTNVOpxXJAgMBAAECgYBDUgNL1EYw0aT2VFY+AzllnBlfviairV20sp1Tp6bjS5XjXR4MhC3DNv/zKcH+siy2DMPI8zwRYMDNJb5Tq62lDB/CWXxuRu0b9+ODXZ3yePvvUKVqc2NXZD/8txjtZh5FzEeLw6MeW1ylFsYZtLH97IJiKWEU9w4B9vWEp5uqAQJBANj/SIYK3CfKbpunqnckkop3udmJ4BUhhaFmD8fZbQmMw6SJSB+o9FB0FTwLmWdc2UV3kbpqyyEZd8h75EBs90ECQQDURB/TjObftKO9dVmK+lxR/imktunFWtQ64k8MehuDnZasLm860pQNpeLI3aDGOlYV+RHllC0tCDCXWrkFycSJAkAb2Jab7OTXjlinTNrJMz5C2p5U1iaVT5nwXkKEKNifMxsgECXbOjkv4dWfwPVMmFOhYHio7W9nrfb7GTrvMYyBAkEAmskqpCuFV+/zzv4505yJocjDOTeg9KctR9srZZ/NXIaYDuq1daGFEQa8f1kOGj8D83Xy1QTehI4KUPR5I31kcQJAFeweluxwANSYxKiugt0iiI6TuPCMPZhJtQ9Y5y/S+aK0OvARHr/aC03g1JfDnt+Uts7NgvYboeQWGG2oJVGWLw==";

            String data = "https://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttpshttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafhttps://ss.fdas&=aafdsafdsafdasgfdsgfdsgfdsgfdsgffdttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aaf://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafttps://ss.fdas&=aafdsafdsafsafdsgdsfg593793274932794732947329874fdsgfdsg";

            byte[] ss = encryptByPublicKey(data.getBytes(), publicKey);

            String req = Base64.encode(ss);

            System.out.println(req);

            System.out.println(URLEncoder.encode(req, "UTF-8"));

            byte[] tt = Base64.decode("PfSa+u8BlL0+692gu+EGnZlYmdDMAjs9VszQ1eBh6pOjl/SRb3auFdXo5yAr/LIEf7nDEghP8S/8dHBeSajvEfBpsAEaJPJ6hFM0J+x3UgSdJPO3e6Y465+ZhitCRI2l9Ih4dWU+NG+WRVTphudX2USxtnBYIBTZc4umMhx2l7Y=l5UGdIcqCBaPtGMGbTlMzwIZK/P8bgOD3Po1LuZG/EsxhRv6uZbsSuGJuO9FPYPWp89lMyiQiG8E/hHVtGQ2azRCi9CoNpyrkEHWKdxZdHYFEOGUG3niNYApjfMES0lPYW30Z+2y44ZwP5xCjp4NTiztRlYIflFSMrlkcyEAR6w=&jvyBrCukPnQ5VzU8ITr3F5Z9DlBKRUXeGl96Ce5rbwtk9nCW7tpIJ5ZG0g8krpRE9qCGvF0MeEArKIbYg+mfbo8AJJxJwiCeLQ+REfLmvL84XUEJ5vOTz1TPu7KtETuP73LYsgzmvEHbeoUkVbaQdlTaKSwqzw/hm4bLrh0kfwg=");

            byte[] aaa = decryptByPrivateKey(tt, privateKey);

            System.out.println("解密数据：" + new String(aaa));
            // System.out.println(aa);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
