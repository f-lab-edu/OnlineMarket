package com.market.user.repository;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.market.user.domain.User;

@Repository
public class InMemoryUserRepository implements UserRepository {
	private final HashMap<String, User> registeredUser = new HashMap<>();

	@Override
	public void insertUser(User user) {
		registeredUser.put(user.getEmail(), user);
	}

	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(registeredUser.get(email));
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		User user = registeredUser.get(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}
}
