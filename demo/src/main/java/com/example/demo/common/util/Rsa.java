package com.example.demo.common.util;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.net.URLDecoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.common.util.SignatureUtil.getPrivateKeyFromPKCS8;

public class Rsa {
    /**
     * 指定加密算法为RSA     用这个  好用 我说的
     */
    private static final String ALGORITHM = "RSA";
    /**
     * 指定公钥存放文件
     */

    private static String PUBLIC_KEY_FILE = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAthHUaZu/XmhGFZ44CE72CZ3Oaoe1/ifoEgUqPz8CWZVhwgDsiJVuWiAugPkgVJJAtUPqbK/0e3g6x0dgwm9efxmU3KFg8uHgp1kb57ME/yJDWIKUjkxT6pBfZdH4by2zJmtxteIXtA2EjJe/qOSpxhVIMHAR/sGB4VE5YlcgEb60SJzKLrTkqErA3fKaXFanTKnJAPKreZN6Gj88hMcpy1iNTW9BQqdm8eLobkuOo19AnHvYLgYpPMP3TfZ8pxtqNhxE2lUutuVRrEnwHyppwUWedh2rQQeaVbO9NREemYaLXFVPcb6OQa4drwPS+4/FNly1656Y3x+zmTojPf+55QIDAQAB";

    /**
     * 指定私钥存放文件
     */
    private static String  PRIVATE_KEY_FILE= "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANFqaISzzjv89+38z7EHn9PDG6I5jNUQWHT/NDjgo4qNiZvQLthlbpNILMGJ3KwqI2TDyojRhwMNABLhzmYoSTlYs3NkowRu8S/L7FSDu11kFRqSU/Ox7tsYIMJALDOXV1eBZ5mEfqz28YJA8vouaT283JMaoqbavGK0N7i7D1BZAgMBAAECgYEAuIEk9w4oPSgjFJYyMsoB4iQ7m5FS6IHPPb1/uEELNc6AGDyymUu8wZzMefRJ7ZHuvx/VuPfKGUEB+KDkJZOG9pwXdAu6hLJS7iUpaK/JbS0DqOtRlFHnNft4YD50tWp/dZr7zNEvcwkRbS5OZJDRZ3IOCf76Z/q7vXimm27eL5UCQQDzL5xRGb0kddBkGjzMQ7IJ04hl2VkaaHBkqLrmIm9OxTeA+0tH17+AXvr2xbhXb4sOqUmmThB3YCTqowDD6NHDAkEA3HNDKFhTR7MEQCDy/OkJVFaIhr6gKavvMicYQx3fprAIlx+cG8xHlR4maHkxHqdEeP/hFCe5YJ3+uy0EPAF3swJBAINEv+xHKIH11ncycn8QS5piRM41dJN8rK6pJbnz/IFYk41cGFa/bu+sVWu/brJD05wmZUsP+HN3wnWlZ1RY6GECQG10AQUYDYlM1bBta5essIgiSrj0DpuCFUoGZSJ1w6SERE+cTyryGxxrktBOU9gPXozhJsSWEJFrAJ24dSDB7ccCQQCWX34oHvbVzLrsfCnQSRDP4HvFoJwQLHTDcP5ujvUnWd8rUNkflxt7Gfgr7sO5dMksMurGxSx4jcZr7TwE77Z8";

   public static void main(String[] args) throws Exception {

        //String source = "你好nihao";// 要加密的字符串

        Map<String,String> map=new HashMap<>();
        map.put("aaa","xiaomimng ");
        map.put("121456","877874 ");
        map.put("ewqwewq","5464564 ");
        System.out.println("准备用公钥加密的字符串为：" + map.toString());
        String source = map.toString();// 要加密的字符串
        String cryptograph = encrypt(source);// 生成的密文
        System.out.print("用公钥加密后的结果为:" + cryptograph);
        System.out.println();


        String target = decrypt("bDfBj7gjbKo7e+HMqi9NPiMBwCmJpd+yrWFs+nWNmCtWNrkt31MF9PoPstV6Zxjf+SqNwp85qifWxC97IZCy8VEsb9kAOdVLxivf3UX5ocwUV52YAlQyPIVnh06jP1iq/1XwedBBsVvp6sQb69X1wiNHh4Lnlu+egRIqBGErTZ8");// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();
    }

/*    *//**
     *                   第二种方法
     * @param str
     * @return
     */
/*    public static void main(String[] args) {
        String a="gVmBqz74JPKoGzk+N8QcmtxFDbAay6LvQxEJJxJjrlbk84bPvMoe4haoFZBq7VrR4+3HmpjZpZPFl2Rq3qtF0F7cVGxDFUqGE1XmuNLII0o/odxU/9WE5xxBXchQphMYAlSvvKecWi55exqlDjnoRlpZQdAOcU2pah/KagcoX0E=";

        //String a="bDfBj7gjbKo7e+HMqi9NPiMBwCmJpd+yrWFs+nWNmCtWNrkt31MF9PoPstV6Zxjf+SqNwp85qifWxC97IZCy8VEsb9kAOdVLxivf3UX5ocwUV52YAlQyPIVnh06jP1iq/1XwedBBsVvp6sQb69X1wiNHh4Lnlu+egRIqBGErTZ8";
        String b="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANFqaISzzjv89+38z7EHn9PDG6I5jNUQWHT/NDjgo4qNiZvQLthlbpNILMGJ3KwqI2TDyojRhwMNABLhzmYoSTlYs3NkowRu8S/L7FSDu11kFRqSU/Ox7tsYIMJALDOXV1eBZ5mEfqz28YJA8vouaT283JMaoqbavGK0N7i7D1BZAgMBAAECgYEAuIEk9w4oPSgjFJYyMsoB4iQ7m5FS6IHPPb1/uEELNc6AGDyymUu8wZzMefRJ7ZHuvx/VuPfKGUEB+KDkJZOG9pwXdAu6hLJS7iUpaK/JbS0DqOtRlFHnNft4YD50tWp/dZr7zNEvcwkRbS5OZJDRZ3IOCf76Z/q7vXimm27eL5UCQQDzL5xRGb0kddBkGjzMQ7IJ04hl2VkaaHBkqLrmIm9OxTeA+0tH17+AXvr2xbhXb4sOqUmmThB3YCTqowDD6NHDAkEA3HNDKFhTR7MEQCDy/OkJVFaIhr6gKavvMicYQx3fprAIlx+cG8xHlR4maHkxHqdEeP/hFCe5YJ3+uy0EPAF3swJBAINEv+xHKIH11ncycn8QS5piRM41dJN8rK6pJbnz/IFYk41cGFa/bu+sVWu/brJD05wmZUsP+HN3wnWlZ1RY6GECQG10AQUYDYlM1bBta5essIgiSrj0DpuCFUoGZSJ1w6SERE+cTyryGxxrktBOU9gPXozhJsSWEJFrAJ24dSDB7ccCQQCWX34oHvbVzLrsfCnQSRDP4HvFoJwQLHTDcP5ujvUnWd8rUNkflxt7Gfgr7sO5dMksMurGxSx4jcZr7TwE77Z8";
        try{
            System.out.println(rsaDecrypt(a,b,"utf-8"));
        }catch (Exception e){
            System.out.println("e");
        }
    }*/

    public static String checkString(String str) {
        int len = str.length();
        int i = 0, j = 0;
        char[] strChar = str.toCharArray();
        for (i = 0; i < len; i++) {
            if (' ' == strChar[i] || '\t' == strChar[i] || '\n' == strChar[i]){
                strChar[i] = '+';
                continue;
            }
        }
        return new String(Arrays.copyOf(strChar, len));
    }

    /**
     * 加密方法
     *
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception {
        byte[] keyBytes = Base64.decode(PUBLIC_KEY_FILE);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }


    /**
     *                         第二种方法 解密
     * @param content   =utf-8
     * @param privateKey
     * @param charset
     * @return
     * @throws Exception
     */

    public static String rsaDecrypt(String content, String privateKey, String charset) throws Exception {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(new ByteArrayInputStream(privateKey.getBytes()));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] encryptedData = (charset == null || charset.isEmpty()) ? Base.decodeBase64(content.getBytes()):Base.decodeBase64(content.getBytes(charset));
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
            throw new Exception("EncodeContent = " + content + ",charset = " + charset, e);
        }
    }





    /**
     * 解密算法
     *
     * @param cryptograph 密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph) throws Exception {
        byte[] keyBytes = Base64.decode(PRIVATE_KEY_FILE);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);

        /** 执行解密操作 */
        byte[] b = null;
        try {
            b = cipher.doFinal(b1);
            return new String(b);
        } catch (Exception e) {

        }
        return "";
    }

    private static Key getKey(String fileName) throws Exception, IOException {
        Key key;
        ObjectInputStream ois = null;
        try {
            /** 将文件中的私钥对象读出 */
            ois = new ObjectInputStream(new FileInputStream(fileName));
            key = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        return key;
    }
}
