package com.market.user.service;

import org.springframework.stereotype.Service;

import com.market.auth.repository.RedisRepository;
import com.market.user.controller.LoginResponse;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.domain.User;
import com.market.user.repository.UserRepository;
import com.market.util.TokenUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenLoginService implements LoginService {
	private final RedisRepository redisRepository;
	private final UserRepository userRepository;

	@Override
	public LoginResponse login(SignInRequestDto dto) {
		User user = dto.toEntity();
		if (userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).isEmpty()) {
			throw new IllegalArgumentException("로그인 정보가 올바르지 않습니다.");
		}
		String token = TokenUtil.createNewToken();
		redisRepository.set(token, user.getEmail());
		return new LoginResponse(token);
	}
}
