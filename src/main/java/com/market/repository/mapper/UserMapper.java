package com.market.repository.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.market.application.domain.User;

@Repository
@Mapper
public interface UserMapper {
	void insertUser(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
}
