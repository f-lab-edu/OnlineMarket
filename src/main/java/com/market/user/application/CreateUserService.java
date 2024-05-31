package com.market.user.application;

import org.springframework.stereotype.Service;

import com.market.user.application.exception.DuplicatedUserEmailException;
import com.market.user.application.exception.errorCode.ApplicationErrorCode;
import com.market.user.application.port.in.command.SignUpCommand;
import com.market.user.application.port.in.usecase.CreateUserUseCase;
import com.market.user.application.port.out.repository.UserRepository;
import com.market.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
	private final UserRepository userRepository;

	@Override
	public void signUp(SignUpCommand command) {
		User user = User.createUser(command);
		if (userRepository.existsByEmail(user.getEmail()).isPresent()) {
			throw new DuplicatedUserEmailException(ApplicationErrorCode.DUPLICATED_USER_EMAIL);
		}
		userRepository.save(user);
	}
}
