package com.market.service;

import org.springframework.stereotype.Service;

import com.market.domain.User;
import com.market.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository = new UserRepository();

	public void signUp(User user) {
		user.createdUser();
		if (!userRepository.insertUser(user)) {
			throw new RuntimeException("회원가입 실패!!");
		}
	}
}
