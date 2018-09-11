package com.unicom.mcloud.devops.plugin.build;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * hash工具类
 * 
 * @author huangjl
 *
 */
public final class HashUtil {

	//private static final Logger LOG = LoggerFactory.getLogger(HashUtil.class);

	/**
	 * 静态类不可构造
	 */
	private HashUtil() {
	}

	/**
	 * 字节转16进制字符串
	 * 
	 * @param src
	 *            待转换的字节组
	 * @return 16进制字符串
	 */
	public static String byte2hex(final byte[] src) {
		final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] str = new char[src.length * 2];
		for (int i = 0; i < src.length; i++) {
			final byte b = src[i];
			str[i * 2] = hexDigits[b >>> 4 & 0xf];
			str[i * 2 + 1] = hexDigits[b & 0xf];
		}
		return new String(str);
	}


	/**
	 * 获得字节组的md5
	 * 
	 * @param source
	 *            待转换的字节组
	 * @return 已转换的字节流
	 */
	public static byte[] getMD5(final byte[] source) {
		byte[] result = new byte[0];
		try {
			final MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(source);
			result = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			//TODO 转换失败
		}
		return result;
	}

}
