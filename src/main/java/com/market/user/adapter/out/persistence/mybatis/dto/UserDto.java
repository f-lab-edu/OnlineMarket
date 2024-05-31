package com.market.user.adapter.out.persistence.mybatis.dto;

import com.market.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String tel;

	public User toEntity() {
		return new User(this.id, this.email, this.password, this.name, this.tel);
	}

	public static UserDto createUserDto(User user) {
		UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getTel());
		return userDto;
	}
}
