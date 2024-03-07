package com.market.auth.repository;

import java.util.Optional;

import com.market.global.dto.RedisTokenDto;

public interface RedisRepository {
	Optional<RedisTokenDto> get(Long key);

	void set(Long key, RedisTokenDto value);
}
