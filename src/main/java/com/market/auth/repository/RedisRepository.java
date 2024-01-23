package com.market.auth.repository;

import java.util.Optional;

public interface RedisRepository {
	Optional<String> get(String token);

	void set(String token, String id);

}
