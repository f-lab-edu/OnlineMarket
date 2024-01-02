package com.market.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.market.domain.User;

@Repository
public class InMemoryUserRepository implements UserRepository {
	private HashMap<String, User> registeredUser = new HashMap<>();

	public boolean insertUser(User user) {
		registeredUser.put(user.getId(), user);
		return true;
	}
}
