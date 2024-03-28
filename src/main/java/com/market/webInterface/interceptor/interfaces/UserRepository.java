package com.market.webInterface.interceptor.interfaces;

import java.util.Optional;

import com.market.application.domain.dto.User;

public interface UserRepository {
	void insertUser(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
}
