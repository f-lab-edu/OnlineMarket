package com.market.user.repository;

import java.util.Optional;

import com.market.user.domain.User;

public interface UserRepository {
	void insertUser(User user);

	User findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
}
