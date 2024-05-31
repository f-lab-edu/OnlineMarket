package com.market.user.application.port.in.usecase;

import com.market.user.application.port.in.command.SignUpCommand;

public interface CreateUserUseCase {
	void signUp(SignUpCommand command);
}
