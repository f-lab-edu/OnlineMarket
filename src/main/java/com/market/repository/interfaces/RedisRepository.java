package com.market.repository.interfaces;

import java.util.Optional;

public interface RedisRepository {
	Optional<String> get(String token);

	void set(String token, String email);
}
