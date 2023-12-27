package com.market.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.market.domain.CreateUserDomain;
import com.market.util.SHA256Util;

@Service
public class UserService {
	public HashMap<String, CreateUserDomain> user = new HashMap();

	public void createUser(CreateUserDomain userDomain) {
		if (CreateUserDomain.isNull(userDomain)) {
			throw new NullPointerException("null 데이터 포함");
		}

		if (CreateUserDomain.isEmpty(userDomain)) {
			throw new IllegalArgumentException("빈 데이터 포함");
		}

		userDomain.setPassword(SHA256Util.encryptSHA256(userDomain.getPassword()));

		user.put(userDomain.getId(), userDomain);
		System.out.println(user.get("minjung123").getPassword());

	}
}
