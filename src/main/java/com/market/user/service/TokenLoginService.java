package com.market.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.auth.repository.RedisRepository;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.repository.UserRepository;

@Service
public class TokenLoginService implements LoginService {
	private final RedisRepository redisRepository;
	private final UserRepository userRepository;

	@Autowired
	public TokenLoginService(@Qualifier("inMemoryRedisRepository") RedisRepository redisRepository,
		UserRepository userRepository) {
		this.redisRepository = redisRepository;
		this.userRepository = userRepository;

	}

	@Override
	public void login(SignInRequestDto dto) {
	}

	@Override
	public void logout() {

	}
}
