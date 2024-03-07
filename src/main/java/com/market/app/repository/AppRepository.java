package com.market.app.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.market.app.domain.App;

@Repository
@Mapper
public interface AppRepository {
	Optional<App> findByName(String name);
}
