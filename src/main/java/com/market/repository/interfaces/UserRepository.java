package com.market.repository.interfaces;

import java.util.Optional;

import com.market.application.domain.User;

public interface UserRepository {
	void insertUser(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
}
