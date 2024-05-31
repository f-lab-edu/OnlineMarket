package com.market.user.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpCommand {
	private String name;
	private String email;
	private String password;
	private String tel;
}
