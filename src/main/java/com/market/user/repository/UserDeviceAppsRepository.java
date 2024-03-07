package com.market.user.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.market.user.domain.UserDeviceApps;

@Repository
@Mapper
public interface UserDeviceAppsRepository {
	Optional<UserDeviceApps> findByUserIdAndDevice(Long userId, String device);

	Long insertUserDeviceApps(UserDeviceApps userDeviceApps);
}
