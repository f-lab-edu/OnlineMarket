package com.market.repository.entity;

import com.market.application.domain.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEntity {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String tel;

	public User toEntity() {
		return User.builder()
			.id(this.getId())
			.email(this.getEmail())
			.password(this.getPassword())
			.name(this.getName())
			.tel(this.getTel())
			.build();
	}
}
