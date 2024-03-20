package com.market.application.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.application.domain.User;
import com.market.application.repository.interfaces.UserRepository;
import com.market.application.service.dto.SignUpRequestDto;
import com.market.global.exception.application.DuplicatedUserEmailException;
import com.market.global.exception.application.UserCreateFailException;
import com.market.global.exception.errorCode.ApplicationErrorCode;
import com.market.global.exception.errorCode.RepositoryErrorCode;
import com.market.global.exception.repository.UserInsertFailException;

@Service
public class CreateUserService {
	private final UserRepository userRepository;

	public CreateUserService(@Qualifier("userRepositoryImpl") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void signUp(SignUpRequestDto dto) {
		User user = dto.toDomain();
		try {
			if (isDuplicatedUser(user.getEmail())) {
				throw new DuplicatedUserEmailException(ApplicationErrorCode.DUPLICATED_USER_EMAIL);
			}
			try {
				userRepository.insertUser(user);
			} catch (Exception e) {
				throw new UserInsertFailException(RepositoryErrorCode.USER_INSERT_FAIL);
			}
		} catch (DuplicatedUserEmailException | UserInsertFailException e) {
			throw new UserCreateFailException(ApplicationErrorCode.USER_CREATE_FAIL, e.getError().getMessage());
		}
	}

	private boolean isDuplicatedUser(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}
