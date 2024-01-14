package com.market.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {
	public static final String TYPE = "SHA-256";

	public static String encryptSHA256(String str) {
		String sha = "";
		try {
			MessageDigest md = MessageDigest.getInstance(TYPE);
			md.update(str.getBytes());
			byte[] data = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte i = 0; i < data.length; i++) {
				sb.append(Integer.toString(((int)data[i] & 0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("암호화 불가", e);
		}
		return sha;
	}
}
