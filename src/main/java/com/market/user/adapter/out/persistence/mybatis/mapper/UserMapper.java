package com.market.user.adapter.out.persistence.mybatis.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.market.global.annotation.ExecutionTimeChecker;
import com.market.user.adapter.out.persistence.mybatis.dto.UserDto;

@Mapper
@ExecutionTimeChecker
public interface UserMapper {
	void insertUser(UserDto user);

	Optional<UserDto> findByEmail(String email);

	Optional<UserDto> findByEmailAndPassword(String email, String password);
}
