package com.market.auth.repository;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.market.global.dto.RedisTokenDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RedisTemplateRepository implements RedisRepository {
	private final RedisTemplate<Long, RedisTokenDto> redisTemplate;

	@Override
	public Optional<RedisTokenDto> get(Long key) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(key));
	}

	@Override
	public void set(Long key, RedisTokenDto value) {
		redisTemplate.opsForValue().set(key, value);
	}
}
