package com.example.demo.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;
public class JYMD5 {

	public final static String MD5Encoder(String s, String charset) {
		try {
			byte[] btInput = s.getBytes(charset);
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * 普通MD5加密 01
	 * <p>
	 *
	 * @Title : getStrMD5
	 *        </p>
	 *        <p>
	 * @Description : TODO
	 *              </p>
	 *              <p>
	 * @Author : HuaZai
	 *         </p>
	 *         <p>
	 * @Date : 2017年12月26日 下午2:49:44
	 *       </p>
	 */
	public static String getStrMD5(String inStr) {
		// 获取MD5实例
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return "";
		}

		// 将加密字符串转换为字符数组
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		// 开始加密
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] digest = md5.digest(byteArray);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			int var = digest[i] & 0xff;
			if (var < 16)
				sb.append("0");
			sb.append(Integer.toHexString(var));
		}
		return sb.toString();
	}
	/**
	 * 普通MD5加密 02
	 * <p>
	 *
	 * @Title : getStrrMD5
	 *        </p>
	 *        <p>
	 * @Description : TODO
	 *              </p>
	 *              <p>
	 * @Author : HuaZai
	 *         </p>
	 *         <p>
	 * @Date : 2017年12月27日 上午11:18:39
	 *       </p>
	 */
	public static String getStrrMD5(String password) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte strTemp[] = password.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte md[] = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 15];
				str[k++] = hexDigits[byte0 & 15];
			}

			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * MD5双重解密
	 * <p>
	 *
	 * @Title : getconvertMD5
	 *        </p>
	 *        <p>
	 * @Description : TODO
	 *              </p>
	 *              <p>
	 * @Author : HuaZai
	 *         </p>
	 *         <p>
	 * @Date : 2017年12月26日 下午3:34:17
	 *       </p>dddd
	 */
	public static String getconvertMD5(String inStr) {
		char[] charArray = inStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) (charArray[i] ^ 't');
		}
		String str = String.valueOf(charArray);
		return str;
	}

	/**
	 * 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
	 * <p>
	 *
	 * @Title : md5Hex
	 *        </p>
	 *        <p>
	 * @Description : TODO
	 *              </p>
	 *              <p>
	 * @Author : HuaZai
	 *         </p>
	 *         <p>
	 * @Date : 2017年12月27日 上午11:28:25
	 *       </p>
	 */
	@SuppressWarnings("unused")
	private static String md5Hex(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(str.getBytes());
			return new String(new Hex().encode(digest));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return "";
		}
	}

	/**
	 * 加盐MD5加密
	 * <p>
	 *
	 * @Title : getSaltMD5
	 *        </p>
	 *        <p>
	 * @Description : TODO
	 *              </p>
	 *              <p>
	 * @Author : HuaZai
	 *         </p>
	 *         <p>
	 * @Date : 2017年12月27日 上午11:21:00
	 *       </p>
	 */
	public static String getSaltMD5(String password) {
		// 生成一个16位的随机数
		Random random = new Random();
		StringBuilder sBuilder = new StringBuilder(16);
		sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
		int len = sBuilder.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sBuilder.append("0");
			}
		}
		// 生成最终的加密盐
		String Salt = sBuilder.toString();
		password = md5Hex(password + Salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = Salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}
		return String.valueOf(cs);
	}

	/**
	 * 验证加盐后是否和原文一致
	 * <p>
	 *
	 * @Title : verifyMD5
	 *        </p>
	 *        <p>
	 * @Description : TODO
	 *              </p>
	 *              <p>
	 * @Author : HuaZai
	 *         </p>
	 *         <p>
	 * @Date : 2017年12月27日 下午2:22:22
	 *       </p>
	 */
	public static boolean getSaltverifyMD5(String password, String md5str) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5str.charAt(i);
			cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
			cs2[i / 3] = md5str.charAt(i + 1);
		}
		String Salt = new String(cs2);
		return md5Hex(password + Salt).equals(String.valueOf(cs1));
	}



	public static void main(String[] args) {
		JYMD5 md = new JYMD5();
	/*	String strMD5 = new String("12345");

		System.out.println("原始：" + strMD5);
		System.out.println("东东的：" + md.getStrrMD5(strMD5));
		System.out.println("MD5后：" + md.getStrMD5(strMD5));
		System.out.println("加密的：" + md.getconvertMD5(strMD5));
		System.out.println("解密的：" + md.getconvertMD5(md.getconvertMD5(strMD5)));

		System.out.println("\t\t=======================================");*/
		// 原文
		String plaintext = "huazai";
		// plaintext = "123456";
		System.out.println("原始：" + plaintext);
		System.out.println("普通MD5后：" + JYMD5.getStrMD5(plaintext));

		// 获取加盐后的MD5值
		String ciphertext = JYMD5.getSaltMD5(plaintext);
		System.out.println("解密的：" + md.getconvertMD5(md.getconvertMD5(plaintext)));
		System.out.println("解密的1：" + md.getconvertMD5(md.getconvertMD5(ciphertext)));
		System.out.println("加盐后MD5：" + ciphertext);
		System.out.println("是否是同一字符串:" + JYMD5.getSaltverifyMD5(plaintext, ciphertext));

		/**
		 * 其中某次DingSai字符串的MD5值
		 */
		String[] tempSalt = { "810e1ee9ee5e28188658f431451a29c2d81048de6a108e8a",
				"66db82d9da2e35c95416471a147d12e46925d38e1185c043",
				"61a718e4c15d914504a41d95230087a51816632183732b5a" };

		for (String temp : tempSalt) {
			System.out.println("是否是同一字符串:" + JYMD5.getSaltverifyMD5(plaintext, temp));
		}

	}


	/*public static void main(String[] args) {
		String s="sssssss";
		String charset="utf-8";
		try {
			byte[] btInput = s.getBytes(charset);
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}
			//32位的加密
			String aa32=sb.toString();
			System.out.println("32位的加密"+aa32);

			//16位的加密
			String bb16=aa32.substring(8, 24);
			System.out.println("16位的加密"+bb16);
		} catch (Exception e) {

		}
	}*/
}
