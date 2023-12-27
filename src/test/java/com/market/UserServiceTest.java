package com.market;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.market.domain.CreateUserDomain;
import com.market.service.UserService;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	@DisplayName("고객 회원가입 성공")
	public void createUserTest() {
		CreateUserDomain user = new CreateUserDomain(
			"minjung123",
			"asdfasdf12",
			"김민정",
			"010-1234-1234");
		userService.createUser(user);
		assertEquals("minjung123", userService.user.get("minjung123").getId());
	}
}