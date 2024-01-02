package com.market;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.market.domain.User;
import com.market.repository.InMemoryUserRepository;
import com.market.service.CreateUserService;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	CreateUserService userService;

	@Autowired
	InMemoryUserRepository userRepository;

	@Test
	@DisplayName("고객 회원가입 성공")
	public void signUpTest() {
		User user = new User(
			"minjung123",
			"asdfasdf12",
			"김민정",
			"010-1234-1234");
		userService.signUp(user);
		assertTrue(userRepository.insertUser(user));
	}
}
