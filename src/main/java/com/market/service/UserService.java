package com.market.service;

import org.springframework.stereotype.Service;

import com.market.domain.CreateUserDomain;
import com.market.util.SHA256Util;

@Service
public class UserService {
	public void createUser(CreateUserDomain userDomain) {
		if (CreateUserDomain.isNull(userDomain)) {
			throw new NullPointerException("null 데이터 포함");
		}

		if (CreateUserDomain.isEmpty(userDomain)) {
			throw new IllegalArgumentException("빈 데이터 포함");
		}

		userDomain.setPassword(SHA256Util.encryptSHA256(userDomain.getPassword()));

	}
}
