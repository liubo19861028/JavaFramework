package com.framework.comm.util;

import java.util.Random;
import java.util.UUID;

 

/**
 * 
 * 接口或类的说明: 字符串的工具类
 *
 * <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-15 上午9:25:35 <br>
 * ==========================
 *
 */
public class StringUtil {

	/**
	 * 该方法是trim一个字符串，返回trim后的字符串
	 *
	 * @param s
	 *            String
	 * @return 如果传入一个null，则返回一个null，如果是字符串，则返回trim后的字符串
	 */
	public static String trim(String s) {
		return s == null ? null : s.trim();
	}

	public static boolean isEmpty(String s) {
		if (s == null || s.trim().length() == 0)
			return true;
		else
			return false;
	}

	/**
	 * 把指定的整形数据转换成指定长度的序列号，前面补0
	 *
	 * @param number
	 * @param length
	 * @return
	 */
	public static String generateSequenceNumber(int number, int length) {
		return generateSequenceNumber((long) number, length);
	}

	/**
	 * 把指定的整形数据转换成指定长度的序列号，前面补0
	 *
	 * @param number
	 * @param length
	 * @return
	 */
	public static String generateSequenceNumber(long number, int length) {
		String num = String.valueOf(number);
		if (num.length() > length)
			throw new IllegalArgumentException("number is longer than length!");
		if (num.length() == length)
			return num;
		int zeroLength = length - num.length();
		StringBuilder zeros = new StringBuilder();
		String zero = "0";
		for (int i = 0; i < zeroLength; i++) {
			zeros.append(zero);
		}
		return zeros + num;
	}

	/**
	 * 填充成指定长度的字符
	 *
	 * @param src
	 * @return
	 */
	public static String fillString(String src, int theLength, char fillChat) {
		return fillString(src, theLength, fillChat, true);
	}

	/**
	 * 填充成指定长度的字符
	 *
	 * @param src
	 * @param theLength
	 * @param fillChat
	 * @param isFillBegin
	 * @return
	 */
	public static String fillString(String src, int theLength, char fillChat, boolean isFillBegin) {
		return fill(src, theLength, fillChat, isFillBegin);
	}

	/**
	 * 填充成指定长度的字符
	 *
	 * @param src
	 * @param theLength
	 * @param fillChat
	 * @param isFillHead
	 * @return
	 */
	public static String fill(String src, int theLength, char fillChat, boolean isFillHead) {
		if (StringUtil.isEmpty(src)) {
			src = "";
		}
		if (src.length() >= theLength) {
			return src;
		}
		int zeroLength = theLength - src.length();
		StringBuilder zeros = new StringBuilder();
		for (int i = 0; i < zeroLength; i++) {
			zeros.append(fillChat);
		}
		if (isFillHead) {
			return zeros + src;
		}
		return src + zeros;
	}

	/**
	 * 获取36位长度的UUID字符串
	 *
	 * @return
	 */
	public static String getUUID36() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取32位长度的UUID字符串
	 *
	 * @return
	 */
	public static String getUUID32() {
		return getUUID36().replaceAll("-", "");
	}

	/**
	 * 方法用途和描述: 判断字符是否为数字
	 *
	 * @param c
	 * @return
	 */
	public static boolean isNumber(char c) {
		return (c > 48 && c < 57);
	}

	/**
	 * 方法用途和描述: 判断字符是否为字母
	 *
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		return (c > 65 && c < 90) || (c > 97 && c < 122);
	}

	/**
	 * 替换掉非字母跟数字的字符
	 *
	 * @param str
	 *            原字符串
	 * @param replaceChar
	 *            用来替换的字符
	 * @return
	 */
	public static String replaceSpecialCharacters(String str, String replaceChar) {
		if (str == null || "".equals(str.trim()))
			return "";
		StringBuffer sb = new StringBuffer(str.length());
		for (int index = 0, length = str.length(); index < length; index++) {
			char c = str.charAt(index);
			if (isNumber(c) || isLetter(c))
				sb.append(c);
			else
				sb.append(replaceChar);
		}
		return sb.toString();
	}

	/**
	 * 把一些html标签转成转义字符（比如把"<"转成"&lt;"）
	 *
	 * @param html
	 * @return
	 */
	public static String htmlToWeb(String html) {
		if (html == null || html.length() == 0)
			return "";
		char[] c = html.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			switch (c[i]) {
			case '>':
				sb.append("&gt;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			default:
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return NumberUtil.isNum(str);
	}

	/**
	 * 替换字符串最后面指定长度的字符串
	 *
	 * @param src
	 * @param repChar
	 * @param repLength
	 * @return
	 */
	public static String replaceLastStr(String src, char repChar, int repLength) {
		if (StringUtil.isEmpty(src)) {
			return "";
		}
		if (src.length() < repLength) {
			return StringUtil.fillString("", repLength, repChar);
		}
		int srcLength = src.length();
		return StringUtil.fillString(src.substring(0, srcLength - repLength), srcLength, repChar, false);
	}

	/**
	 * 描述：生成指定长度的随机字符串
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789"; // 生成字符串从此序列中取
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取字串字节长度，中文占两个字节
	 * 
	 * @param s
	 * @return
	 */
	public static int getStringCount(String s) {
		s = s.replaceAll("[^\\x00-\\xff]", "**");
		int length = s.length();
		return length;

	}
}
