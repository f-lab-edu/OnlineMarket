package com.market.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.app.domain.App;
import com.market.app.repository.AppRepository;
import com.market.auth.repository.RedisRepository;
import com.market.global.dto.RedisTokenDto;
import com.market.user.controller.LoginResponse;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.domain.User;
import com.market.user.domain.UserDeviceApps;
import com.market.user.repository.UserDeviceAppsRepository;
import com.market.user.repository.UserRepository;
import com.market.util.TokenUtil;

@Service
public class TokenLoginService implements LoginService {
	private final UserRepository userRepository;
	private final RedisRepository redisRepository;
	private final AppRepository appRepository;
	private final UserDeviceAppsRepository userDeviceAppsRepository;

	public TokenLoginService(UserRepository userRepository,
		@Qualifier("redisTemplateRepository") RedisRepository redisRepository,
		AppRepository appRepository, UserDeviceAppsRepository userAppRepository) {
		this.userRepository = userRepository;
		this.redisRepository = redisRepository;
		this.appRepository = appRepository;
		this.userDeviceAppsRepository = userAppRepository;
	}

	@Override
	public LoginResponse login(SignInRequestDto dto) {
		User loginUser = dto.toEntity();
		Optional<User> user = userRepository.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
		if (user.isEmpty()) {
			throw new IllegalArgumentException("로그인 정보가 올바르지 않습니다.");
		}
		Optional<App> app = appRepository.findByName(dto.getAppName());
		if (app.isEmpty()) {
			throw new IllegalArgumentException("존재하지 않는 앱입니다.");
		}
		if (app.get().isMembership() && !user.get().isMembership()) {
			throw new IllegalArgumentException("멤버쉽이 필요한 서비스입니다.");
		}
		Optional<UserDeviceApps> userDeviceApp =
			userDeviceAppsRepository.findByUserIdAndDevice(user.get().getId(), dto.getDevice());
		Long userDeviceAppId;
		if (userDeviceApp.isEmpty()) {
			userDeviceAppId = userDeviceAppsRepository.insertUserDeviceApps(UserDeviceApps
				.builder()
				.userId(user.get().getId())
				.appId(app.get().getId())
				.device(dto.getDevice())
				.build()
			);
		} else {
			userDeviceAppId = userDeviceApp.get().getId();
		}
		String token = TokenUtil.createNewToken();
		redisRepository.set(userDeviceAppId, new RedisTokenDto(token, user.get().getId()));
		return new LoginResponse(token, userDeviceAppId);
	}
}
