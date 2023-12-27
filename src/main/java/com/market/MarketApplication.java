package com.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.market.domain.User;
import com.market.service.UserService;

@SpringBootApplication
public class MarketApplication {

	private static UserService userService = new UserService();

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
		userService.signUp(new User(
			"minjung123",
			"asdfasdf12",
			"김민정",
			"010-1234-1234"
		));
	}

}
