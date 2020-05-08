package com.example.demo.common.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class aa {

    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYh0Sk6kfhaQD/NzM2+6bwZVtV63WUk4SGdFFf1YqzeOhxUWxnOQcYs4d4irRMIt/jLQKWQjwMUrEVUe12JRBsTAILHgaI+OCv+jV90U88LPEO+lAdBj8E8txXddtXkckxkyb4vYhbpOLGFSPKJMctl9rhaT4GmcUncVEgtRrj9wIDAQAB";
    public static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJiHRKTqR+FpAP83Mzb7pvBlW1XrdZSThIZ0UV/VirN46HFRbGc5Bxizh3iKtEwi3+MtApZCPAxSsRVR7XYlEGxMAgseBoj44K/6NX3RTzws8Q76UB0GPwTy3Fd121eRyTGTJvi9iFuk4sYVI8okxy2X2uFpPgaZxSdxUSC1GuP3AgMBAAECgYBU5l5IKXvvNSJv7MreHP9UKibH0XM2S1JmANLWtCcrNO61PKxJXI3dqviKGTK4emUCr6gT+TQxa8nT4zF5JLJZpkidd7N3/DN2WrOsDtMNWrWUFcuEafjUL9rhgjxeKpnup1dXZz5B4x+70zwc+Cq3QkJkMf0fY6KspnNAtcR2+QJBAOq8oNUr9ogkj0QtULR01ghgUJS0xMSODO0afhPLDPzcL/UmLJFd8Rq+MmJBOzq61ScqW0DmI1mW1FjsF3Jl6NsCQQCmWEmg+6RNhhSOIP/ocaMQwwb6jdWKcxB0FSPeVabdfQ6+1tG5+bgcao0YZaCIqIWvB9JN68p1cCftpnuCWX4VAkEAjrbyPcjF0Y4QwI2ghKaIPn14YIDrJOmMeFdkEdqg4ddpHO4BqLvpy62Zal1oilNvJqm8419IXL60CgA4XSgSgwJBAKChpQg1uGW8UmElcLnP8n2fN0AAcvMtRfgy6d4rq+U6fGDBJOmIQcf/BJ1IlgshYWayEN16EpnxYj1VhPNM5DkCQB3I1Jyx1Rgs8VvOGwnPJ3AsvOadKtUZvFIW5+qNUx3WxizeuJ03P/VA9Kmwez49Vtpe2wfS592Vwx9Btnwt+g4=";
    public static String pei="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDT/PKpzsjTPnX+GnsIXcdHQrAsz3XGm7tlB4wndfyK0IuuC2ysFz8aTvLVsMA2UL5PYlxb8V3kOlGx0lNobljaPfo6HHgQuGH6Mq0NsGy+di0GO+u/doTgGCew6CULv5Zk8j6JSKZEEITuENTozM15ui40rFHDM98BT1X2AT5Seai5fYtjso0uFqjJ6VfzhfRVp8QvrwzXyyVSpILGdY48FbOmvj7qGknPoCzKHsoF60mj+fAHGlzbVn59vmp5ImFrHB06WbFohletUHna/M1dq6Ky20nd99HbNRxjrRkTeWGvTYwV0O+NMEHd1OquWLiglzSMvSEppucRxwaauasnAgMBAAECggEBAMfQ77LbtTRY9cM0lq/GJWaoF4PFcVBpznNmb9dqQUdfV6oQHrj/mH1l2UNPPX51EU2me0Q2+Ld9uS+R/wi6qoncTZak6w9nnQLG7ZOvrnDA16O+q3Jndsv8OL1ptDGkv1AbfpLMpdwWbhYwS1+8XBnXQmJn4xHKPPPxIiel+GanivU13qlJVlqfxHwab0zNJRpyulhwmVFyuDgGRQRbYhkz4eRfBKWv40W1knEp1VuWjwmAaMDeIiCPIMzygzSZe1pliq/gD/yapN/Do8umWby4v2R8Kf0fWgWKpReapGpqlFFn9pIMfXQcPFxijysy9d+uh/2RRZl0NbFaRYMlFMECgYEA7pZWVVL9rXVNJOO1SKgGeu9vByaJWffc8MQEStO5o33JNFXMLDroWogP778bKfqxQDdlNTWRRxAbNeH09JKBWf02M4uQVp5rLyTF30+lB8MPoCeOSoQc5RcSXTG+oPRFcpRP83bxgnTFE768tu6AK6P9/VpWGAqLygH1D4SEI/ECgYEA43WkmMYakHPxubDQk/oQVC2no/vVFoQlXXtHbAM1oOA0y+6VuIWE9nE5oBW60Zwygirh0WYVJQQ9zlCQvkT/GeVlDwx5X5PMdpt0AN2z7okJSDf0qLdy+hLMCENyHKGqHQ5CNwSOppJ04CBPIWIyNXfX7zI7Qqn8LUrEPHhn+JcCgYEA6RF5ku54m7ssOi4riKeDdRJy/qQ5pubIMXb6znIYNcsKA5MQ2eBN3Spvjv+8NH0OLDIxLxJ+MdJdEn0CQuzfaZ4/FYlv3f3xrOMaXTS7fWKi4AvkJ4z5gR3RtbKDi040jWKu26JE+PDDGK/KQFWqZXdVBer6kY+z0QmqcJc+UrECgYEA3eZD+CKzgZ8K/+uF4AbBbU3I0Mp6XGf4h9UfnTkYVWTFPPn5qDThvQZCgHESezDVeXVaLc1X7W/Q1/hy7Jwk/l9zEpZJxjtK6Z7IMZwX0CpyGrkz/nBRQ7OYxqr9pP72FTh20nOossoIKSp76AaDEvRifWDUvTlV4d2hyZhxXesCgYAcoGH0ww0Uv4jUBaJBm7pmpKQXmmVZoRBxnpEeKi25d3d7GiDSaAFD80pVQIQIyd9RON3z+VvZGvSgbHTrCcHi1qZc7Bc3ypqk64ufMy/eHcdR2HgWJ4bA1yOoCg9Lh3LMfT6JMe17EKhoDNXkb3P6ttX8pIcAzWyqWsk3fXA6TQ==";
    private static String algorithm = "RSA"; //$NON-NLS-1$
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static String data = "test jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihgtest jiojiogeiojigouihg"; //$NON-NLS-1$

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
       /* String test = testEncrypt(privateKey,pei);
        System.out.println(test);
        System.out.println(test.length());
        String testDecrypt = testDecrypt(publicKey, test);
        System.out.println(testDecrypt);
        System.out.println(testDecrypt.equals(pei));*/
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY,-(24*7));
        System.out.println(calendar.getTime());
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println(weekOfYear);
/*        String year = new SimpleDateFormat("yyyy").format(calendar.getTime());
        String month = new SimpleDateFormat("MM").format(calendar.getTime());
        String dd = new SimpleDateFormat("dd").format(calendar.getTime());*/
        String yearWeek = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        System.out.println(yearWeek);
        System.out.println(12*13);






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
        String encodeToString =java.util.Base64.getEncoder().encodeToString(encryptedData);
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

        byte[] bytes = java.util.Base64.getDecoder().decode(data);
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
