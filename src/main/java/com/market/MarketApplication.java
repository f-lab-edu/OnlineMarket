package com.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.market.domain.CreateUserDomain;
import com.market.service.UserService;

@SpringBootApplication
public class MarketApplication {

	@Autowired
	static UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);

		userService.createUser(new CreateUserDomain(
			"minjung123",
			"asdfasdf12",
			"김민정",
			"010-1234-1234"));

	}

}
