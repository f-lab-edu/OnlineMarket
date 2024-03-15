package com.market.application.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.application.domain.User;
import com.market.application.repository.interfaces.UserRepository;
import com.market.application.service.dto.SignUpRequestDto;

@Service
public class CreateUserService {
	private final UserRepository userRepository;

	public CreateUserService(@Qualifier("userRepositoryImpl") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void signUp(SignUpRequestDto dto) {
		User user = dto.toDomain();
		if (isDuplicatedUser(user.getEmail())) {
			throw new IllegalArgumentException("이미 등록된 회원입니다");
		}
		userRepository.insertUser(user);
	}

	private boolean isDuplicatedUser(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}
