package com.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.domain.User;
import com.market.repository.UserRepository;

@Service
public class CreateUserService {
	final private UserRepository userRepository;

	@Autowired
	public CreateUserService(@Qualifier("inMemoryUserRepository") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void signUp(User user) {
		user.createdUser();
		if (!userRepository.insertUser(user)) {
			throw new RuntimeException("회원가입 실패!!");
		}
	}
}
