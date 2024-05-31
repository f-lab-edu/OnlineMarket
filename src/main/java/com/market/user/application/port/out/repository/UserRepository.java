package com.market.user.application.port.out.repository;

import java.util.Optional;

import com.market.user.domain.User;

public interface UserRepository {
	void save(User user);

	Optional<User> existsByEmail(String email);
}
