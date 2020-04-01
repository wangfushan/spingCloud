package com.example.demo.common.util;

import java.util.Random;

/**
 * 类功能说明：生成随机码工具类<br/>
 * 类修改者：<br/>
 * 修改日期：<br/>
 * 修改说明：<br/>
 * 公司名称：广州市领课科技有限公司 <br/>
 * 作者：roncoo-lrx <br/>
 * 创建时间：2016年9月27日 下午2:55:43 <br/>
 * 版本：V1.0 <br/>
 */
public class RandomUtils {

    private static final String INT = "0123456789";
    private static final String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random rd = new Random();

    private RandomUtils() {
    }

    /**
     * 函数功能说明：生成0-9的随机码. Administrator 2015-9-8 修改者名字 修改日期 修改内容
     * 
     * @参数： @param length
     * @参数： @return
     * @return String
     * @throws
     */
    public static String randomInt(int length) {
        return random(length, RndType.INT);
    }

    /**
     * 函数功能说明：生成a-z的随机码. Administrator 2015-9-8 修改者名字 修改日期 修改内容
     * 
     * @参数： @param length
     * @参数： @return
     * @return String
     * @throws
     */
    public static String randomStr(int length) {
        return random(length, RndType.STRING);
    }

    /**
     * 函数功能说明：成长数字加字母的随机码. Administrator 2015-9-8 修改者名字 修改日期 修改内容
     * 
     * @参数： @param length
     * @参数： @return
     * @return String
     * @throws
     */
    public static String randomAll(int length) {
        return random(length, RndType.ALL);
    }

    private static String random(int length, RndType rndType) {
        StringBuilder s = new StringBuilder();
        char num = 0;
        for (int i = 0; i < length; i++) {
            if (rndType.equals(RndType.INT)) {
                num = INT.charAt(rd.nextInt(INT.length()));// 产生数字0-9的随机数
            } else if (rndType.equals(RndType.STRING)) {
                num = STR.charAt(rd.nextInt(STR.length()));// 产生字母a-z的随机数
            } else {
                num = ALL.charAt(rd.nextInt(ALL.length()));
            }
            s.append(num);
        }
        return s.toString();
    }

    public static enum RndType {
        INT, STRING, ALL
    }


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
}
