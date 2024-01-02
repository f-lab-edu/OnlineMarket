package com.market.user.repository;

import com.market.user.domain.User;

public interface UserRepository {
	void insertUser(User user);

	User findByEmail(String email);
}
