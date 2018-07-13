package com.framework.comm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 接口或类的说明: 摘要生成器，用于对各种信息进行摘要
 *
 * <br>==========================
 * <br> 公司：南京壹号家信息科技有限公司
 * <br> 开发：yisheng.lu@yihaojiaju.com
 * <br> 版本：1.0
 * <br> 创建时间：2017-11-15 上午9:25:35
 * <br>==========================
 *
 */
public class DigesterUtil {

	public static String digest(String key, String toDigest) {
		return digest(key, toDigest, true);
	}

	/**
	 * 以key+toDigest进行md5摘要
	 *
	 * @param key
	 * @param toDigest
	 * @param flag
	 *            为true时， key放前面; false时， key放后面
	 * @return
	 */
	public static String digest(String key, String toDigest, boolean flag) {
		String result = "";
		if (!(StringUtil.isEmpty(key) || StringUtil.isEmpty(toDigest))) {
			try {
				String clearText = toDigest;
				if (flag) {
					clearText = key + clearText;
				} else {
					clearText = clearText + key;
				}
				return digestForMd5(clearText);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 *
	 * 接口或类的说明: 算法枚举对象
	 *
	 * <br>=
	 * ========================= <br>
	 * 公司：南京壹号家信息科技有限公司 <br>
	 * 开发：yisheng.lu@yihaojiaju.com <br>
	 * 版本：1.0 <br>
	 * 创建时间：2012-11-7 上午9:11:39 <br>=
	 * =========================
	 *
	 */
	public enum Algorithm {
		/**
		 * SHA-1算法编码
		 */
		SHA {
			@Override
			public String toString() {
				return "SHA-1";
			}
		},
		/**
		 * MD5算法编码
		 */
		MD5 {
			@Override
			public String toString() {
				return "MD5";
			}
		};

		public String getName() {
			return toString();
		}
	}

	/**
	 * 功能描述：将明文用MD5或SHA-1算法生成摘要信息
	 *
	 * @param algorithm
	 *            加密算法：枚举对象Algorithm（包含有MD5或SHA-1两种算法）
	 * @param plainText
	 *            要产生摘要的明文
	 * @return 摘要信息
	 * @throws NoSuchAlgorithmException
	 *             当找不到算法的时候抛出
	 * @throws UnsupportedEncodingException
	 */
	public synchronized static String digest(Algorithm algorithm,
			String plainText) {
		if (plainText == null)
			return null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm.toString());
			md.update(plainText.getBytes("utf-8"));
			return byte2hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述：将明文用MD5算法生成摘要信息
	 *
	 * @param plainText
	 *            要产生摘要的明文
	 * @return MD5摘要信息
	 */
	public static String digestForMd5(String plainText) {
		return digest(Algorithm.MD5, plainText);
	}

	/**
	 * 字节数组转十六进制
	 *
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs.append("0");
			}
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static void main(String[] args) {
		String s = "service=adRecharge&charset=utf-8&mchntLoginUserName=miugobuyadmin&mchntLoginPwd=21218CCA77804D2BA1922C33E0151105&token20130401141648881106&miugoCardNo=1698881800007809&CAIC=000000000002501&adOrderNum=201304011417473962694";
		System.out.println(DigesterUtil.digestForMd5(s));
	}
}
