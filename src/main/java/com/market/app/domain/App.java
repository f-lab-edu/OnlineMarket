package com.market.app.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class App {
	private Long id;
	private String name;
	private boolean isMembership;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
}
