package com.market.application.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.application.domain.dto.User;
import com.market.application.exception.DuplicatedUserEmailException;
import com.market.application.exception.errorCode.ApplicationErrorCode;
import com.market.application.repository.interfaces.UserRepository;
import com.market.application.usecase.dto.SignUpRequestDto;

@Service
public class CreateUserUseCase {
	private final UserRepository userRepository;

	public CreateUserUseCase(@Qualifier("userRepositoryImpl") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void signUp(SignUpRequestDto dto) {
		User user = dto.toDomain();
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new DuplicatedUserEmailException(ApplicationErrorCode.DUPLICATED_USER_EMAIL);
		}
		userRepository.insertUser(user);
	}
}
