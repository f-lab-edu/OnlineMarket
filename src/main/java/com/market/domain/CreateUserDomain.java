package com.market.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDomain implements UserDomain {
	private String id;
	private String password;
	private String name;
	private String tel;

	public CreateUserDomain(String id, String password, String name, String tel) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.tel = tel;
	}

	public static boolean isNull(CreateUserDomain userDomain) {
		return userDomain.getId() == null || userDomain.getPassword() == null
			|| userDomain.getName() == null || userDomain.getTel() == null;
	}

	public static boolean isEmpty(CreateUserDomain userDomain) {
		return userDomain.getId().isEmpty() || userDomain.getPassword().isEmpty()
			|| userDomain.getName().isEmpty() || userDomain.getTel().isEmpty();
	}

}
