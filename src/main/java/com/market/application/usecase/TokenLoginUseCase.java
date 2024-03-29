package com.market.application.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.application.domain.dto.User;
import com.market.application.repository.interfaces.RedisRepository;
import com.market.application.repository.interfaces.UserRepository;
import com.market.application.usecase.dto.LoginResponseDto;
import com.market.application.usecase.dto.SignInRequestDto;
import com.market.global.util.TokenUtil;

@Service
public class TokenLoginUseCase implements LoginUseCase {
	private final UserRepository userRepository;
	private final RedisRepository redisRepository;

	public TokenLoginUseCase(@Qualifier("userRepositoryImpl") UserRepository userRepository,
		@Qualifier("redisTemplateMapper") RedisRepository redisRepository) {
		this.userRepository = userRepository;
		this.redisRepository = redisRepository;
	}

	@Override
	public LoginResponseDto login(SignInRequestDto dto) {
		User user = dto.toDomain();
		if (userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).isEmpty()) {
			throw new IllegalArgumentException("로그인 정보가 올바르지 않습니다.");
		}
		String token = TokenUtil.createNewToken();
		redisRepository.set(token, user.getEmail());
		return new LoginResponseDto(token);
	}
}
