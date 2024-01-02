package com.market.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.user.domain.User;
import com.market.user.dto.SignUpRequestDto;
import com.market.user.repository.UserRepository;

@Service
public class CreateUserService {
	final private UserRepository userRepository;

	@Autowired
	public CreateUserService(@Qualifier("inMemoryUserRepository") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void signUp(SignUpRequestDto dto) {
		User user = dto.toEntity();
		userRepository.insertUser(user);
	}
}
