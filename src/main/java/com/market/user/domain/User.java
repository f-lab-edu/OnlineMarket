package com.market.user.domain;

import com.market.util.SHA256Util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
	private String email;
	private String password;
	private String name;
	private String tel;

	@Builder
	public User(String email, String password, String name, String tel) {
		this.email = email;
		this.password = SHA256Util.encryptSHA256(password);
		this.name = name;
		this.tel = tel;
	}
}
