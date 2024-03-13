package com.market.application.service;

import com.market.application.dto.LoginResponseDto;
import com.market.application.dto.SignInRequestDto;

public interface LoginService {
	LoginResponseDto login(SignInRequestDto dto);
}
