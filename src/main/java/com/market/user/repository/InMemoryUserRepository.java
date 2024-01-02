package com.market.user.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.market.user.domain.User;

@Repository
public class InMemoryUserRepository implements UserRepository {
	private HashMap<String, User> registeredUser = new HashMap<>();

	public void insertUser(User user) {
		registeredUser.put(user.getId(), user);
	}
}
