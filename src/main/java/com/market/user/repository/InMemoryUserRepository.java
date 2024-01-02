package com.market.user.repository;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.market.user.domain.User;

@Repository
public class InMemoryUserRepository implements UserRepository {
	private HashMap<String, User> registeredUser = new HashMap<>();

	@Override
	public void insertUser(User user) {
		registeredUser.put(user.getEmail(), user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(registeredUser.get(email));
	}
}
