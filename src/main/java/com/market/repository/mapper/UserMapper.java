package com.market.repository.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.market.repository.dto.UserDto;

@Mapper
public interface UserMapper {
	void insertUser(UserDto user);

	Optional<UserDto> findByEmail(String email);

	Optional<UserDto> findByEmailAndPassword(String email, String password);
}
