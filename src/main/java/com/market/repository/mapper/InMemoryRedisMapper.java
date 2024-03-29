package com.market.repository.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.market.application.repository.interfaces.RedisRepository;

@Repository
public class InMemoryRedisMapper implements RedisRepository {
	private final Map<String, String> data = new HashMap<>();

	@Override
	public Optional<String> get(String token) {
		return Optional.of(data.get(token));
	}

	@Override
	public void set(String token, String id) {
		data.put(token, id);
	}
}
