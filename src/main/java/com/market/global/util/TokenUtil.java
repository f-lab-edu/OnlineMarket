package com.market.global.util;

import java.util.UUID;

public class TokenUtil {
	public static String createNewToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
