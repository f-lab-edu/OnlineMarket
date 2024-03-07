package com.market.user.domain;

import java.time.LocalDateTime;

import com.market.util.SHA256Util;

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
	private boolean isMembership;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;

	public User(Long id, String email, String password, String name, String tel, boolean isMembership,
		LocalDateTime updatedAt, LocalDateTime createdAt) {
		this.id = id;
		this.email = email;
		this.password = SHA256Util.encryptSHA256(password);
		this.name = name;
		this.tel = tel;
		this.isMembership = isMembership;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
}
