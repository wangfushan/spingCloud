package com.example.demo.common.util;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

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
    private static String  PRIVATE_KEY_FILE= "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2EdRpm79eaEYVnjgITvYJnc5qh7X+J+gSBSo/PwJZlWHCAOyIlW5aIC6A+SBUkkC1Q+psr/R7eDrHR2DCb15/GZTcoWDy4eCnWRvnswT/IkNYgpSOTFPqkF9l0fhvLbMma3G14he0DYSMl7+o5KnGFUgwcBH+wYHhUTliVyARvrRInMoutOSoSsDd8ppcVqdMqckA8qt5k3oaPzyExynLWI1Nb0FCp2bx4uhuS46jX0Cce9guBik8w/dN9nynG2o2HETaVS625VGsSfAfKmnBRZ52HatBB5pVs701ER6ZhotcVU9xvo5Brh2vA9L7j8U2XLXrnpjfH7OZOiM9/7nlAgMBAAECggEBAJACaE2kKGjG93BP9if0NL2EeWlfsH64F+Jw/WqLLxOmiW1HB9A8yc01mTLgWkcqa040yu5u+mTSw0MuXQVNy6Oim1ErwCA8s1IKtHy+55vaAQS4PEKVcuacjShfPS7LKeGgqI9hprf+3THLMYioXPTVjoTpkAI+EroiYc479ZyyL0XUeKPdU7fsEtxxw8G4vzikmvFHIa/V420q1HFXhBxdR646Rce85wrhbDQTzBKJNh2GQ0OKp0KIQNPqAZ8wyLK57n6ZNE0Nta7aS3FZOFgzaIEt7BD9ZsDnMs+VKiDMmYDUQCC4L73GeLZdUPlMd5lC4sBTRRpsPazFDdj9jyECgYEA9cZqrTZXXyBiOji4LDJBuSISMFiqrLeM5ZUL2Em7mkA5n8DgLVX3LWp/2SWqSMJP0UNU6Pj4uIWe754ir4/jGTV9iPVMJqdE2sySDpfcLDo1hsysgDYuZkSfIeu+wYajPu4CmZNKpgLzvz6cfj7kogMyY8QgKfnSJK1sItO03k0CgYEAvaTuDXYhew4TOxFwVrBjZjxQBNeK+Nd4lJ0mx8a7gmhP2p87WDR7VDP8u30d8PByiyxjG1lZ70FRaTt9lgolR2Es9Vjj8EBAdcjeIO52wLppBwVfBxC/al6dt5hl5aFIXBWE5/iEVKB2BFDvXKVArC6VusMCSmGshDHLlqusBfkCgYBVtc9n/oZzxzBaS4N27kPbU1VyhMlomE5LljHeSMBi57jwzGuhg9RCg1x4ltkOrKz3Nnr7EDB08SxfNg+0mAJDvVK9G4ZmCHlLsIHTEwSWa+pUyzXxNHPz+ERiqPsKNHDjtk8zzZj/0hG9BRHAOSC0m1bMUg5BdTziy5PlhV0TFQKBgCFbyaQEi48hwrA5ORGGpXVuVoUDhSaQgF1j51Zs1r7xqlpCeCXj1W3SVzqDn6iqIvvoKua8qN01GCTymfyyfCw6I7ZklHPGWZBebPRN/gWYxLC/eR+9Cm6jhgqte/8Qh7Hq9x320RAxyZCoy/H3/5BKAXyAoP6Ef9whBjbSq8fBAoGAPk2wqJtnC/s9Q/y1FdcqgIRcH4WBUuYTff4oLiGclno6HKRAMVek3HE4DjxaxSINBNzXIXiYGIKwq11yky5KkgnUSmPKHaslo8XXbD3MIe0nuYg3MXjOsdlBtHlKlNdj2jttZ995/luHed9x8t6LYwsu+yVR62sYyqVx0z8gVQ8=";

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

        String target = decrypt(cryptograph);// 解密密文
        System.out.println("用私钥解密后的字符串为：" + target);
        System.out.println();
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
