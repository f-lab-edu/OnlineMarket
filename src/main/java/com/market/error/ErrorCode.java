package com.market.error;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
	BAD_REQUEST(400, "BAD_REQUEST"),
	UNAUTHORIZED(401, "UNAUTHORIZED"),
	FORBIDDEN(403, "FORBIDDEN"),
	NOT_FOUND(404, "NOT_FOUND"),
	INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");
	private final int status;
	private final String code;
}
