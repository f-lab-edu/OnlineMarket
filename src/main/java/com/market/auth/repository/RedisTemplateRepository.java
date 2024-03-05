package com.market.auth.repository;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RedisTemplateRepository implements RedisRepository {
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public Optional<String> get(String token) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(token));
	}

	@Override
	public void set(String token, String email) {
		redisTemplate.opsForValue().set(token, email);
	}
}
