package com.market.user.service;

import com.market.user.controller.dto.SignInRequestDto;

public interface LoginService {
	void login(SignInRequestDto dto);
}
