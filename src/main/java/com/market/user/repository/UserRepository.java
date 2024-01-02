package com.market.user.repository;

import java.util.Optional;

import com.market.user.domain.User;

public interface UserRepository {
	void insertUser(User user);

	Optional<User> findByEmail(String email);
}
