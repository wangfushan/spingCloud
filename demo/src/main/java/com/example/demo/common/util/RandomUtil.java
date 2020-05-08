package com.example.demo.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 随机数生成工具类
 *
 * @author：shenjialong
 */
public class RandomUtil {
    private static final Logger log = LogManager.getLogger(RandomUtil.class);

    /**
     * UUID自动生成 一般用于不自增ID
     * @return
     */

    public static String UTest() {
        UUID uuid = UUID.randomUUID();
        String aa=uuid.toString().replaceAll("-","");
        return aa;

    }

  /*  public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        String aa=uuid.toString().replaceAll("-","");

        System.out.println(aa);
    }
*/

    /**
     * 创建随机数(纯数字、数字+小写字母)
     *
     * @param numberFlag
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

    /**
     * 创建随机数（数字+大小写字母）
     *
     * @param length
     * @return
     */
    public static String createRandom(int length) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < length; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    public static String urlRandomCode(String code) {
        String passwordTem = "";
        try {
            byte[] decode = Base64.decode(code);
            String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALJI6MNbAA3IoNHS2oz6KEW36NhdGi5ludET3/9AuvAP4gblmvzSEEBnYAU1yOBgMZkr8CO+WY2l8sulbNruSk9L8eGV8Bajj6zyUA9+lacka6VBI1T00nlPCdhDI9jcyKZx2Rbu1LrburxE3diukltutElasJeV+oSN2tS1TdPBAgMBAAECgYEAihrebA4/EW5b3Y33jINv1Ons7kXYtR5J/cLmnqU26iVzkqIkGDplKm6pu/949w6s/RlDMX0PhCGL3eLsWv+CAXF1FIjWiiLgO03ULqtnbz8Xy6SRIohnHj3c6GJ/OnkWpDrD5sSXxL57f8obTTFLBW6pyFIMH3XtTl+hrQMFDO0CQQD9EzjZEcR/EhB/TdplsEbd9LHkOMsd86AsbqVoekZJ/yEGgjr8x66hDvl9t/+DSBeimxhjBd2QdxHTzf133t57AkEAtFhnXYWPcu1a8eoYlABoCxZYT1KDIowYWZIBMS+hpjiRlxawVY9RWj3hVp2eprfFtmitt+PsPbHb8R7imkNf8wJAZrirpWgR0uYZLiMCl63iResxXYoz37dod0B+ARdIenaFCChKGxpN5m+2kvHhU38qoMTFNERIN4AtC0ScMlCTCwJBAKh+/DxNoCQBjXn+fO4ynwLU4rdh4TFy8QEmv8Rju0UhDy25vhCCccC9lf6mD2e2xZvSm5P2+G7/8P2MHK2Ci28CQFAmIl6CPbCj/aiLZ74PyGSwycC4iousyKJ7KUkYugSB05Q6I3cC7mgtCkoPJVlv/t0bf3Mq6Bu92aeRE4+7Q7w=";
            passwordTem = new String(RsaUtil.decryptByPrivateKey(decode, privateKey));
            log.debug("---------{}", passwordTem);
        } catch (Exception e) {
            log.warn("=====>>密码解密异常：{}", e.getMessage());
        }
        return passwordTem;
    }

    /**
     * @param urlparam 带分隔的url参数  转map专用
     * @return
     */
    public static Map<String, String> url2map(String urlparam) {
        Map<String, String> map = new HashMap<String, String>();
        String[] param = urlparam.split("&");
        for (String keyvalue : param) {
            String[] pair = keyvalue.split("=");
            if (pair.length == 2) {
                map.put(pair[0], pair[1]);
            }
        }
        return map;
    }

    private static char charB1 = '+';
    private static char charB2 = '/';
    private static char charB3 = '=';
    private static char charS1 = '-';
    private static char charS2 = '_';
    private static char charS3 = '~';

    public static String base64ToSelf(String strCode) {
        String strReturn = strCode;
        strReturn = strReturn.replace(charB1, charS1);
        strReturn = strReturn.replace(charB2, charS2);
        strReturn = strReturn.replace(charB3, charS3);
        return strReturn;
    }

    public static String selfToBase64(String strCode) {
        String strReturn = strCode;
        strReturn = strReturn.replace(charS1, charB1);
        strReturn = strReturn.replace(charS2, charB2);
        strReturn = strReturn.replace(charS3, charB3);
        return strReturn;
    }

/*    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa8eed8ef45d79d1&redirect_uri=https://www.baidu.com&response_type=code&scope=snsapi_base&state=123#wechat_redirect", "UTF-8");
//        String encode = URLEncoder.encode("https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=20000067&scope=auth_base&redirect_uri=http://pay.sandgate.cn/lijun/gateway/tradeCreateAuth/aLiPay", "UTF-8");
//        String encode = URLEncoder.encode("http://pay.sandgate.cn/lijun/gateway/tradeCreateAuth/aLiPay", "UTF-8");
        System.out.println("**********" + encode);
        String strName = "BY5d/tFP2pRMS+UoprLFvdrXvVvKhdNBWyJf34CEHHF0K8wfqKmUvT0iMROVdxKE7gbc2grCw7VVTVOLcDjMijOmTd46gionV0QziflYt+98j0V3uWCYRicyRlOph7u3CRgh9GolP4oH4HCSMBkGtLopqOvpx2Y5GR7Ouw4nLxY=";
        String name = RandomUtil.urlRandomCode(strName);
        System.out.println("#######" + name);
        int i = 1;
        i++;

    }*/

}
