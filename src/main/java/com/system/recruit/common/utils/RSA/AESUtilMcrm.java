package com.system.recruit.common.utils.RSA;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AESUtilMcrm {
	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return encodeByte2HexString(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密
	 *
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(decodeHexString2Byte(content));
			return new String(result, "UTF-8"); // 加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将字节数组转换成16进制的字符串
	 *
	 * @param bytes
	 * @return
	 */
	public static String encodeByte2HexString(byte[] bytes) throws Exception {
		if (bytes == null || bytes.length <= 0) {
			throw new Exception("转换失败");
		}
		try {
			return new String(Hex.encodeHex(bytes));
		} catch (Exception e) {
			throw new Exception("转换失败");
		}
	}

	/**
	 * 将16进制的字符串转换成字节数组
	 *
	 * @param hexStr
	 * @return
	 */
	public static byte[] decodeHexString2Byte(String hexStr) throws Exception {
		if (hexStr == null || hexStr.length() <= 0) {
			throw new Exception("转换失败");
		}
		try {
			return Hex.decodeHex(hexStr.toCharArray());
		} catch (Exception e) {
			throw new Exception("转换失败");
		}
	}


}
