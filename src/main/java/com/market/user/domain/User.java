package com.market.user.domain;

import com.market.global.util.SHA256Util;
import com.market.user.application.port.in.command.SignUpCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String tel;

	public static User createUser(SignUpCommand command) {
		User user = new User(0L, command.getEmail(), SHA256Util.encryptSHA256(command.getPassword()), command.getName(),
			command.getTel());
		return user;
	}
}
