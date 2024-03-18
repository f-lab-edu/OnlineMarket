package com.market.repository.implementation;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.market.application.domain.User;
import com.market.application.repository.interfaces.UserRepository;
import com.market.repository.dto.UserDto;
import com.market.repository.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
	private final UserMapper userMapper;

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user.toEntity());
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<UserDto> userDto = userMapper.findByEmail(email);
		if (userDto.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(userDto.get().toEntity());
		}
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		Optional<UserDto> userDto = userMapper.findByEmailAndPassword(email, password);
		if (userDto.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(userDto.get().toEntity());
		}
	}
}
