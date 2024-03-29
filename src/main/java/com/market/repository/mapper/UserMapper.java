package com.market.repository.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.market.repository.entity.UserEntity;

@Mapper
public interface UserMapper {
	void insertUser(UserEntity user);

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
