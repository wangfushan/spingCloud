package com.example.demo.common.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class rsa2 {




    /**
     * 加密（对外暴露）
     * 如果使用 公钥 对数据 进行加密，只有用对应的 私钥 才能 进行解密。
     * 如果使用 私钥 对数据 进行加密，只有用对应的 公钥 才能 进行解密。
     *
     * @param keyStr
     * @param data
     * @return
     * @throws Exception
     */
  /*  public static String encryptData(String keyStr, String data, Boolean isPublicKey) throws Exception {
        if (StringUtils.isEmpty(keyStr)) {
            return "";
        }
        return encryptBASE64(encrypt(getKey(keyStr, isPublicKey), data.getBytes()));
    }*/

    public static String encryptData(String keyStr, String data, Boolean isPublicKey) throws Exception {
            String result = "";
            try {
                // 将Base64编码后的公钥转换成PublicKey对象
                byte[] buffer = Base64.decode(keyStr);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
                PublicKey publicKey = keyFactory.generatePublic(keySpec);
                // 加密
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] inputArray = data.getBytes();
                int inputLength = inputArray.length;
                System.out.println("加密字节数：" + inputLength);
                // 最大加密字节数，超出最大字节数需要分组加密
                int MAX_ENCRYPT_BLOCK = 117;
                // 标识
                int offSet = 0;
                byte[] resultBytes = {};
                byte[] cache = {};
                while (inputLength - offSet > 0) {
                    if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                        cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                        offSet += MAX_ENCRYPT_BLOCK;
                    } else {
                        cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                        offSet = inputLength;
                    }
                    resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                    System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
                }
                result = Base64.encode(resultBytes);
            } catch (Exception e) {
                System.out.println("rsaEncrypt error:" + e.getMessage());
            }

            return result;

    }

    /**
     * 解密（对外暴露）
     * 如果使用 公钥 对数据 进行加密，只有用对应的 私钥 才能 进行解密。
     * 如果使用 私钥 对数据 进行加密，只有用对应的 公钥 才能 进行解密。
     *
     * @param keyStr
     * @param data
     * @return
     * @throws Exception
     */
  /*  public static String decryptData(String keyStr, String data, Boolean isPublicKey) throws Exception {
        if (StringUtils.isEmpty(keyStr)) {
            return "";
        }
        return new String(decrypt(getKey(keyStr, isPublicKey), decryptBASE64(data)), "UTF-8");
    }*/

   public static String decryptData(String keyStr, String data, Boolean isPublicKey) throws Exception {
       String result = "";
       try {
           // 将Base64编码后的公钥转换成PublicKey对象
           byte[] buffer = Base64.decode(keyStr);
           KeyFactory keyFactory = KeyFactory.getInstance("RSA");
           X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
           PublicKey publicKey = keyFactory.generatePublic(keySpec);
           // 加密
           Cipher cipher = Cipher.getInstance("RSA");
           cipher.init(Cipher.ENCRYPT_MODE, publicKey);
           byte[] inputArray = data.getBytes();
           int inputLength = inputArray.length;
           System.out.println("加密字节数：" + inputLength);
           // 最大加密字节数，超出最大字节数需要分组加密
           int MAX_ENCRYPT_BLOCK = 117;
           // 标识
           int offSet = 0;
           byte[] resultBytes = {};
           byte[] cache = {};
           while (inputLength - offSet > 0) {
               if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                   cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                   offSet += MAX_ENCRYPT_BLOCK;
               } else {
                   cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                   offSet = inputLength;
               }
               resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
               System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
           }
           result = Base64.encode(resultBytes);
       } catch (Exception e) {
           System.out.println("rsaEncrypt error:" + e.getMessage());
       }

       return result;

    }


    /**
     * 加密
     *
     * @param key
     * @param srcBytes
     * @return
     */
    private static byte[] encrypt(Key key, byte[] srcBytes) {
        if (key != null) {
            try {
                //Cipher负责完成加密或解密工作，基于RSA
                Cipher cipher = Cipher.getInstance("RSA");
                //对Cipher对象进行初始化
                cipher.init(Cipher.ENCRYPT_MODE, key);
                //加密，并返回
                return cipher.doFinal(srcBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 解密
     *
     * @param key
     * @param encBytes
     * @return
     */
    private static byte[] decrypt(Key key, byte[] encBytes) {
        if (key != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                //对Cipher对象进行初始化
                cipher.init(Cipher.DECRYPT_MODE, key);
                //解密并返回结果
                return cipher.doFinal(encBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据key获取公有或者私有key对象
     *
     * @param keyStr
     * @param isPublicKey
     * @return
     * @throws Exception
     */
    private static Key getKey(String keyStr, Boolean isPublicKey) throws Exception {
        if (isPublicKey) {
            return getPublicKey(keyStr);
        } else {
            return getPrivateKey(keyStr);
        }
    }

    /**
     * 根据公有key获取公有key对象
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static RSAPublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * 根据私有key获取私有对象
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static RSAPrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公有/私有Key
     *
     * @return
     */
    private static KeyPair getRSAKey() {
        KeyPair keyPair = null;
        try {
            //生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            //初始化密钥对生成器，密钥大小为1024位
            keyPairGen.initialize(1024);
            //生成一个密钥对，保存在keyPair中
            keyPair = keyPairGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }

    /**
     * 对字符串进行BASE64Decoder
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] decryptBASE64(String key) {
        return java.util.Base64.getDecoder().decode(key);
    }

    /**
     * 对字节数组进行BASE64Encoder
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static String encryptBASE64(byte[] key) {
        return java.util.Base64.getEncoder().encodeToString(key);
    }

/*    public static void main(String[] args) {
        // 生成的一对key保存好
        try {
            //得到私钥和公钥
          *//*  KeyPair keyPair = getRSAKey();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();*//*

            String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk7066jSxqERgLvUDy0ldC0DWSNvYjx+5rlVy0WKKvFcpCoJNhye9sj3jUaqEqUU4FACooQGl0igt/ufez29HdzsqTQhvImyFqOVZgRDKWGanIPF8mE+BVhcn3ru+5qtdvrg0Phyq5+9x8Ulcji0IF9MCU3qTqbH47NpfsySbfEbm1T1wbrT86ahlKKqlf1RlC0W9XLFby9IvN4qVFQfx5bEGfEPdURWPFOmHWqNVPnM7SP0fQX8X/8bqYkpquVKUCFs6jCeRoyx8JsD0Tldab7S8p3jIQyzNaTMMqLutC/53G/ccjH4t3Z60r6BsAYrp2KXOX3DNQbtGLgmZtVz+GwIDAQAB";
            String priKey ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTvTrqNLGoRGAu9QPLSV0LQNZI29iPH7muVXLRYoq8VykKgk2HJ72yPeNRqoSpRTgUAKihAaXSKC3+597Pb0d3OypNCG8ibIWo5VmBEMpYZqcg8XyYT4FWFyfeu77mq12+uDQ+HKrn73HxSVyOLQgX0wJTepOpsfjs2l+zJJt8RubVPXButPzpqGUoqqV/VGULRb1csVvL0i83ipUVB/HlsQZ8Q91RFY8U6Ydao1U+cztI/R9Bfxf/xupiSmq5UpQIWzqMJ5GjLHwmwPROV1pvtLyneMhDLM1pMwyou60L/ncb9xyMfi3dnrSvoGwBiunYpc5fcM1Bu0YuCZm1XP4bAgMBAAECggEAWHFC0Kwk4oe0UMgKsmrD0ZXJ8inyFZQ3cx7iMmjchu1iu1DQGqX20Lpt2BjNfAvdC1Rtnp0+6HV3O48RenqyfLUlmKIA7cmrpjVOhI5Jo+woBvep0ABge+o2ywb3vF1ALIlXdQtyYJCeVITHbLGWk7SVQuYxebf6Pcho2/SBRD7A3bTbTuU7o5wschFYDRtfP5JdthGK8zzPirsS5CLxojHINPJ0kBnGa6qJ006pMJP93cMcYwh0DZbP8TxP1VGvnWySLwofhUBE0busJFXvEMVRVRH2rdEHH6apxk1RtaNPnBfsH4Z+664/7kcxzRrVP02Rq1vfDqNPAd2Z4fEyiQKBgQDDkUonsZF2MgV0rnDU5igAW5ZLAaeW+GN2ARgNhZbvk/U/oLsB+FJmNHbBVYSKUNMqRkEHmVuKZtgUBNW0HObM5NJmT1XCtLktaYYyiwlmJuPqqeq60bUVM4ZkTBqJBqlxonNtMxVLUrsYuIqrxjPhVN6QlD5Xxq4ckLsSETnJ5wKBgQDBZGUgtyHTgzIAksZTczvNplMF58HZT0fk2WHSOCfvjVgitqMi/SkXeKxQubgn5YUWtW6KV2KSeKx0MBQdcVoRs1wlyNcQXOcJYIzPoqBPycGOrmqTu0t1x47UHOySLZyN/ZX63+UYHNQsYCvTKm3jcdXdRk89Dfz53nnGATRrrQKBgQC+9ENa5Kfqp8bp+lYTmzNdvg/O5yuzXo8HLuREd7c78NVbrl5K+yPBxeJavlyvDWJSecyPVe+rAOZlDZ94wWuejqLd4QMVHY9eRxYQje+8faa3f/fBOHg+3lOP5TtXpliWqw9XhbR6Gqqy5aYJOXt4vnye4RDKdiWVoL/4UTeC6wKBgD8/Dxeg7K4YtOydE8FM6Vz7LSG8RphEJlYAhc5TUTQ1vII1yqpxstObQndwFXruxBTGJy2VIIaYcoXZB2sNnBA34UX2JeeTCnf1nHd3TwhpCiUYg4Bm+2m8ZsiV4QR5aD7DlR4jxO+a1gThOtPnh2Kf4vpnuRhVj8tD5/1KfZNNAoGARPwvsD06PC2SSomMJMDwAIYu28B+QJpsbiluYld19uYFFTtkUVkSWPAxwM+hNcki6OyWYom/hxZ2LIdIhUfv9/rQz7teYcOHwHWeT4gfg2h3RYN8Mwt6dSJYXS0RVTnfK/Y8gsmtS9um6gD9exDzncyqT8/ozAbOPCc5vki31BY=";
            System.out.println("公钥：" + pubKey);
            System.out.println("私钥：" + priKey);

            // 测试
            String message =priKey;

            System.out.println("明文：" + message);
            String jiami = encryptData(pubKey, message, true);
            System.out.println("公钥加密后：" + jiami);
            String jiemi = decryptData(priKey, jiami, false);
            System.out.println("用私钥解密后的结果是:" + jiemi);

            jiami = encryptData(priKey, message, false);
            System.out.println("私钥加密后：" + jiami);
            jiemi = decryptData(pubKey, jiami, true);
            System.out.println("用公钥解密后的结果是:" + jiemi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    /**
     * 以下为第二种加密解密方法  比较好用一点
     */

    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQgEoj3z9JrdPNI23DbMQkl3gkGuDke7iBr5yrYyqolkTyxuBLWFwHNuGv4VKOj9fXg61QxpaJ/fxDBvMvmkBSRowHBloGFceVTx8wV/8u0DcjvTCu0IZ1zp6wjG6xBn5j66Sg/q+9hvaY2p7fkKmsvcW6VoNPgQHU1Cf01DLZmQIDAQAB+oXcINOiE3AsuZ4VJmwNZg9Y/7fY+OFRS2JAh5YMsrv2qyoGP+Z9ksre26NYR+Lt91B2lhdwJHLpQpziaANZm/ONb31fj/lwIDAQAB";
    public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANCASiPfP0mt080jbcNsxCSXeCQa4OR7uIGvnKtjKqiWRPLG4EtYXAc24a/hUo6P19eDrVDGlon9/EMG8y+aQFJGjAcGWgYVx5VPHzBX/y7QNyO9MK7QhnXOnrCMbrEGfmPrpKD+r72G9pjant+Qqay9xbpWg0+BAdTUJ/TUMtmZAgMBAAECgYBSozY/Z4FW+31h5fPgK+DFu/8TGFAgXuTvCaJnz2Md9IkZTDejxT6cYWUr53toI5zhvz/XLw6FXNQ54KxMJq/s9PiZYUgq/PMrnyU4gBSTm5BmiWjdaGicVEZ1lofHjpkAchPNW/CzwxD8AeKI7QaObE+EkWbLAi6sa+nRdHKgrQJBAOwYLD2DncU15XCKS0RNzTrNohdBQcisOPHdtQO0CGZlxx3xjuU4WL6/EpdmbjTeYbOSDKCmY5vyVbYZdOWfEs8CQQDiFIwWpvW2WLxLVw3i2P55WmMMXuecwEzg++ae3Ht7nW0zNcWSsyvHh40sM8XqEzmWOzMY6JOePbkuVfWTc4cXAkBRzf5mQhiEoKwjVofF3v9hhKbJT/8vPR1uENgLtHHEqTdZFL3ihqeZUDNs6jz9bKCFy/E8KOsSueEg+6kZdwjZAkEAj2RW4fstd2VasDJb5ViaNqAEmJENOBej60L6KCJR07qqy0M8t+oaR2iLOtDvo6Jj8QxFQXQqRMCDVodAxjANKwJAL3KuaqA6kdy9RxdV3uP8nRXLY7C/1ZIK6U0pyZqKXEwpD+7Ar3hwwhPz9TeuoqjB/cCknZjw70BQFQ0/VUHW2g==";

    final  static String PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTvTrqNLGoRGAu9QPLSV0LQNZI29iPH7muVXLRYoq8VykKgk2HJ72yPeNRqoSpRTgUAKihAaXSKC3+597Pb0d3OypNCG8ibIWo5VmBEMpYZqcg8XyYT4FWFyfeu77mq12+uDQ+HKrn73HxSVyOLQgX0wJTepOpsfjs2l+zJJt8RubVPXButPzpqGUoqqV/VGULRb1csVvL0i83ipUVB/HlsQZ8Q91RFY8U6Ydao1U+cztI/R9Bfxf/xupiSmq5UpQIWzqMJ5GjLHwmwPROV1pvtLyneMhDLM1pMwyou60L/ncb9xyMfi3dnrSvoGwBiunYpc5fcM1Bu0YuCZm1XP4bAgMBAAECggEAWHFC0Kwk4oe0UMgKsmrD0ZXJ8inyFZQ3cx7iMmjchu1iu1DQGqX20Lpt2BjNfAvdC1Rtnp0+6HV3O48RenqyfLUlmKIA7cmrpjVOhI5Jo+woBvep0ABge+o2ywb3vF1ALIlXdQtyYJCeVITHbLGWk7SVQuYxebf6Pcho2/SBRD7A3bTbTuU7o5wschFYDRtfP5JdthGK8zzPirsS5CLxojHINPJ0kBnGa6qJ006pMJP93cMcYwh0DZbP8TxP1VGvnWySLwofhUBE0busJFXvEMVRVRH2rdEHH6apxk1RtaNPnBfsH4Z+664/7kcxzRrVP02Rq1vfDqNPAd2Z4fEyiQKBgQDDkUonsZF2MgV0rnDU5igAW5ZLAaeW+GN2ARgNhZbvk/U/oLsB+FJmNHbBVYSKUNMqRkEHmVuKZtgUBNW0HObM5NJmT1XCtLktaYYyiwlmJuPqqeq60bUVM4ZkTBqJBqlxonNtMxVLUrsYuIqrxjPhVN6QlD5Xxq4ckLsSETnJ5wKBgQDBZGUgtyHTgzIAksZTczvNplMF58HZT0fk2WHSOCfvjVgitqMi/SkXeKxQubgn5YUWtW6KV2KSeKx0MBQdcVoRs1wlyNcQXOcJYIzPoqBPycGOrmqTu0t1x47UHOySLZyN/ZX63+UYHNQsYCvTKm3jcdXdRk89Dfz53nnGATRrrQKBgQC+9ENa5Kfqp8bp+lYTmzNdvg/O5yuzXo8HLuREd7c78NVbrl5K+yPBxeJavlyvDWJSecyPVe+rAOZlDZ94wWuejqLd4QMVHY9eRxYQje+8faa3f/fBOHg+3lOP5TtXpliWqw9XhbR6Gqqy5aYJOXt4vnye4RDKdiWVoL/4UTeC6wKBgD8/Dxeg7K4YtOydE8FM6Vz7LSG8RphEJlYAhc5TUTQ1vII1yqpxstObQndwFXruxBTGJy2VIIaYcoXZB2sNnBA34UX2JeeTCnf1nHd3TwhpCiUYg4Bm+2m8ZsiV4QR5aD7DlR4jxO+a1gThOtPnh2Kf4vpnuRhVj8tD5/1KfZNNAoGARPwvsD06PC2SSomMJMDwAIYu28B+QJpsbiluYld19uYFFTtkUVkSWPAxwM+hNcki6OyWYom/hxZ2LIdIhUfv9/rQz7teYcOHwHWeT4gfg2h3RYN8Mwt6dSJYXS0RVTnfK/Y8gsmtS9um6gD9exDzncyqT8/ozAbOPCc5vki31BY=";
    final  static String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk7066jSxqERgLvUDy0ldC0DWSNvYjx+5rlVy0WKKvFcpCoJNhye9sj3jUaqEqUU4FACooQGl0igt/ufez29HdzsqTQhvImyFqOVZgRDKWGanIPF8mE+BVhcn3ru+5qtdvrg0Phyq5+9x8Ulcji0IF9MCU3qTqbH47NpfsySbfEbm1T1wbrT86ahlKKqlf1RlC0W9XLFby9IvN4qVFQfx5bEGfEPdURWPFOmHWqNVPnM7SP0fQX8X/8bqYkpquVKUCFs6jCeRoyx8JsD0Tldab7S8p3jIQyzNaTMMqLutC/53G/ccjH4t3Z60r6BsAYrp2KXOX3DNQbtGLgmZtVz+GwIDAQAB";
    private static String algorithm = "RSA"; //$NON-NLS-1$
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static String data = "test jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihg"; //$NON-NLS-1$

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        String test = testEncrypt("ss",PRIVATE_KEY);
        System.out.println(test);
        String testDecrypt = testDecrypt(publicKey, test);
        System.out.println(testDecrypt);
        System.out.println(privateKey);

        System.out.println(testDecrypt.equals(privateKey));


    }


    /**
     * 加密
     * @param key
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public static String testEncrypt(String key,String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException{
        byte[] decode = java.util.Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        PrivateKey generatePrivate = kf.generatePrivate(pkcs8EncodedKeySpec);
        Cipher ci = Cipher.getInstance(algorithm);
        ci.init(Cipher.ENCRYPT_MODE, generatePrivate);

        byte[] bytes = data.getBytes();
        int inputLen = bytes.length;
        int offLen = 0;//偏移量
        int i = 0;
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte [] cache;
            if(inputLen - offLen > 117){
                cache = ci.doFinal(bytes, offLen,117);
            }else{
                cache = ci.doFinal(bytes, offLen,inputLen - offLen);
            }
            bops.write(cache);
            i++;
            offLen = 117 * i;
        }
        bops.close();
        byte[] encryptedData = bops.toByteArray();
        String encodeToString = java.util.Base64.getEncoder().encodeToString(encryptedData);
        return encodeToString;
    }




    /**
     * 解密
     * @param key
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static String testDecrypt(String key,String data) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
        byte[] decode = java.util.Base64.getDecoder().decode(key);
//		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode); //java底层 RSA公钥只支持X509EncodedKeySpec这种格式
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        PublicKey generatePublic = kf.generatePublic(x509EncodedKeySpec);
        Cipher ci = Cipher.getInstance(algorithm);
        ci.init(Cipher.DECRYPT_MODE,generatePublic);

        byte[] bytes =java.util.Base64.getDecoder().decode(data);
        int inputLen = bytes.length;
        int offLen = 0;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte[] cache;
            if(inputLen - offLen > 128){
                cache = ci.doFinal(bytes,offLen,128);
            }else{
                cache = ci.doFinal(bytes,offLen,inputLen - offLen);
            }
            byteArrayOutputStream.write(cache);
            i++;
            offLen = 128 * i;

        }
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return new String(byteArray);
    }

}
