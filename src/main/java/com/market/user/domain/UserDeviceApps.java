package com.market.user.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDeviceApps {
	private Long id;
	private Long userId;
	private String device;
	private Long appId;
	private LocalDateTime lastAccessedAt;
}
