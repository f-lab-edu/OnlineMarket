package com.market.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String id;
	private String password;
	private String name;
	private String tel;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
