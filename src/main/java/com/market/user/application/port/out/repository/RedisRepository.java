package com.market.user.application.port.out.repository;

import java.util.Optional;

public interface RedisRepository {
	Optional<String> get(String token);

	void set(String token, String email);
}
