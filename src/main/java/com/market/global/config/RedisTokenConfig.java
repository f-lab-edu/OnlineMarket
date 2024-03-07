package com.market.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.market.global.dto.RedisTokenDto;
import com.market.global.properties.RedisProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class RedisTokenConfig {
	private final RedisProperties redisProperties;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(redisProperties.getHost());
		config.setPort(redisProperties.getPort());
		config.setPassword(redisProperties.getPassword());
		return new LettuceConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<Long, RedisTokenDto> redisTemplate() {
		RedisTemplate<Long, RedisTokenDto> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(Long.class));
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisTokenDto.class));
		return redisTemplate;
	}
}
