// package com.market.user;
//
// import static org.assertj.core.api.Assertions.*;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.BDDMockito.*;
//
// import java.util.Optional;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.http.HttpHeaders;
//
// import com.market.auth.AuthInterceptor;
// import com.market.auth.define.HeaderKey;
// import com.market.auth.exception.UnauthorizedException;
// import com.market.auth.repository.InMemoryRedisRepository;
// import com.market.util.TokenUtil;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
//
// @ExtendWith(MockitoExtension.class)
// public class AuthInterceptorTest {
// 	@InjectMocks
// 	private AuthInterceptor authInterceptor;
// 	@Mock
// 	private InMemoryRedisRepository redisRepository;
// 	@Mock
// 	private HttpServletRequest request;
// 	@Mock
// 	private HttpServletResponse response;
// 	final String token = TokenUtil.createNewToken();
//
// 	@DisplayName("인증 살패_잘못된 헤더")
// 	@Test
// 	public void invaildHeaderAuthorization() throws Exception {
// 		// given
// 		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("");
// 		// when
// 		final RuntimeException result = assertThrows(UnauthorizedException.class,
// 			() -> authInterceptor.preHandle(request, response, any(Object.class)));
// 		//then
// 		assertThat(result.getMessage()).isEqualTo("인증되지 않은 사용자입니다.");
// 	}
//
// 	@DisplayName("인증 살패_유효하지 않은 토큰")
// 	@Test
// 	public void invaildTokenAuthorization() throws Exception {
// 		// given
// 		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + "");
// 		when(redisRepository.get("")).thenReturn(null);
// 		// when
// 		final RuntimeException result = assertThrows(UnauthorizedException.class,
// 			() -> authInterceptor.preHandle(request, response, new Object()));
// 		//then
// 		assertThat(result.getMessage()).isEqualTo("인증되지 않은 사용자입니다.");
// 	}
//
// 	@DisplayName("인증 성공")
// 	@Test
// 	public void successAuthorization() throws Exception {
// 		// given
// 		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + token);
// 		when(redisRepository.get(token)).thenReturn(Optional.of("test@test.com"));
// 		// when
// 		assertThat(authInterceptor.preHandle(request, response, new Object())).isTrue();
// 		//then
// 		then(redisRepository).should(times(1)).get(any(String.class));
// 	}
// }
