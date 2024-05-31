package com.market.user.adapter.out.persistence.mybatis.mapper;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.market.global.annotation.ExecutionTimeChecker;
import com.market.user.application.port.out.repository.RedisRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
@ExecutionTimeChecker
public class RedisTemplateMapper implements RedisRepository {
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
