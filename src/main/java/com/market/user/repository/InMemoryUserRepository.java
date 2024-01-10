package com.market.user.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.market.user.domain.User;

@Repository
public class InMemoryUserRepository implements UserRepository {
	private final HashMap<String, User> registeredUser = new HashMap<>();

	@Override
	public void insertUser(User user) {
		registeredUser.put(user.getEmail(), user);
	}

	@Override
	public User findByEmail(String email) {
		return registeredUser.get(email);
	}
}
