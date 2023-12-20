package com.market.service;

import org.springframework.stereotype.Service;

import com.market.dto.UserDto;
import com.market.utils.SHA256Util;

@Service
public class UserService {
	public void signUp(UserDto userDto) {
		if (userDto.getId() == null || userDto.getPassword() == null || userDto.getName() == null
			|| userDto.getTel() == null) {
			throw new NullPointerException("null 데이터 포함");
		}
		if (userDto.getId().isEmpty() || userDto.getPassword().isEmpty() || userDto.getName().isEmpty()
			|| userDto.getTel().isEmpty()) {
			throw new NullPointerException("비어있는 데이터 포함");
		}
		// id 중복체크
		if (isDuplicatedId(userDto.getId())) {
			throw new RuntimeException("중복된 아이디입니다.");
		}
		userDto.setPassword(SHA256Util.encryptSHA256(userDto.getPassword()));
		//DB.insert..
	}

	public boolean isDuplicatedId(String str) {
		int count = 0;
		//DB.idCount..
		return count == 1;
	}
}
