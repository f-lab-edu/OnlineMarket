package com.market.repository.implementation;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.market.application.domain.dto.User;
import com.market.application.repository.interfaces.UserRepository;
import com.market.repository.entity.UserEntity;
import com.market.repository.exception.UserInsertFailException;
import com.market.repository.exception.errorCode.RepositoryErrorCode;
import com.market.repository.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
	private final UserMapper userMapper;

	@Override
	public void insertUser(User user) {
		try {
			userMapper.insertUser(user.toEntity());
		} catch (Exception e) {
			throw new UserInsertFailException(RepositoryErrorCode.USER_INSERT_FAIL);
		}
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<UserEntity> userDto = userMapper.findByEmail(email);
		if (userDto.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(userDto.get().toEntity());
		}
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		Optional<UserEntity> userDto = userMapper.findByEmailAndPassword(email, password);
		if (userDto.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(userDto.get().toEntity());
		}
	}
}
