package com.market.service;

import org.springframework.stereotype.Service;

import com.market.domain.CreateUserDomain;

@Service
public class UserService {
	public void createUser(CreateUserDomain userDomain) {
		if (CreateUserDomain.isNull(userDomain)) {
			throw new NullPointerException("null 데이터 포함");
		}

		if (CreateUserDomain.isEmpty(userDomain)) {
			throw new IllegalArgumentException("빈 데이터 포함");
		}

	}
}
