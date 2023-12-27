package com.market.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.market.domain.User;

@Service
public class UserService {
	public HashMap<String, User> user = new HashMap();

	public void createUser(User user) {

		user.createdUser();

		this.user.put(user.getId(), user);

	}
}
