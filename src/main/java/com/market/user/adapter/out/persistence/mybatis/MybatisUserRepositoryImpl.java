package com.market.user.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.market.user.adapter.out.persistence.exception.UserInsertFailException;
import com.market.user.adapter.out.persistence.exception.errorCode.RepositoryErrorCode;
import com.market.user.adapter.out.persistence.mybatis.dto.UserDto;
import com.market.user.adapter.out.persistence.mybatis.mapper.UserMapper;
import com.market.user.application.port.out.repository.UserRepository;
import com.market.user.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisUserRepositoryImpl implements UserRepository {
	private final UserMapper userMapper;

	@Override
	public void save(User user) {
		try {
			userMapper.insertUser(UserDto.createUserDto(user));
		} catch (Exception e) {
			throw new UserInsertFailException(RepositoryErrorCode.USER_INSERT_FAIL);
		}
	}

	@Override
	public Optional<User> existsByEmail(String email) {
		Optional<UserDto> userDto = userMapper.findByEmail(email);
		if (userDto.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(userDto.get().toEntity());
		}
	}
}
