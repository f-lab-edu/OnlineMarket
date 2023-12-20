package com.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.dto.UserDto;
import com.market.service.UserService;

@RestController
@RequestMapping("/users/")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("signUp")
	public void signUp(@RequestBody UserDto userDto) {
		userService.signUp(userDto);
	}
}
