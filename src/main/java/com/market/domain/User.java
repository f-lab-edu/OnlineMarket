package com.market.domain;

import com.market.util.SHA256Util;

import lombok.Getter;

@Getter
public class User {
	private String id;
	private String password;
	private String name;
	private String tel;

	public User(String id, String password, String name, String tel) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.tel = tel;
	}

	public User createdUser() {
		if (isNull()) {
			throw new NullPointerException("null 데이터 포함");
		}

		if (isEmpty()) {
			throw new IllegalArgumentException("빈 데이터 포함");
		}

		this.password = SHA256Util.encryptSHA256(this.password);

		return this;
	}

	private boolean isNull() {
		return this.id == null || this.password == null
			|| this.name == null || this.tel == null;
	}

	private boolean isEmpty() {
		return this.id.isEmpty() || this.password.isEmpty()
			|| this.name.isEmpty() || this.tel.isEmpty();
	}

}
