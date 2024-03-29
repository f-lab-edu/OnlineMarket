package com.market.application.usecase;

import com.market.application.usecase.dto.LoginResponseDto;
import com.market.application.usecase.dto.SignInRequestDto;

public interface LoginUseCase {
	LoginResponseDto login(SignInRequestDto dto);
}
