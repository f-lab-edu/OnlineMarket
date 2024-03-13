package com.market.application.domain;

import com.market.global.util.SHA256Util;
import com.market.repository.dto.UserDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String tel;

	public User(Long id, String email, String password, String name, String tel) {
		this.id = id;
		this.email = email;
		this.password = SHA256Util.encryptSHA256(password);
		this.name = name;
		this.tel = tel;
	}

	public UserDto toEntity() {
		return new UserDto(this.getId(), this.getEmail(), this.getPassword(), this.getName(), this.getTel());
	}
}
