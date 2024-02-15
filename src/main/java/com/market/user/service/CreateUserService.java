package com.market.user.service;

import org.springframework.stereotype.Service;

import com.market.user.controller.dto.SignUpRequestDto;
import com.market.user.domain.User;
import com.market.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateUserService {
	private final UserRepository userRepository;

	public void signUp(SignUpRequestDto dto) {
		User user = dto.toEntity();
		if (isDuplicatedUser(user.getEmail())) {
			throw new IllegalArgumentException("이미 등록된 회원입니다");
		}
		userRepository.insertUser(user);
	}

	private boolean isDuplicatedUser(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}
