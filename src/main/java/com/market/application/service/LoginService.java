package com.market.application.service;

import com.market.application.service.dto.LoginResponseDto;
import com.market.application.service.dto.SignInRequestDto;

public interface LoginService {
	LoginResponseDto login(SignInRequestDto dto);
}
