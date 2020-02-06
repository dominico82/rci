package kr.co.rci.esign.admin.security;

import static kr.co.rci.esign.admin.config.SecurityConfig.USERNAME_PARAM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.AdminBean;
import kr.co.rci.esign.admin.service.AdminService;
import kr.co.rci.esign.admin.service.AdminUserDetailsService;

/**
 * 로그인 실패 핸들러
 * <p>로그인 실패 세션의 결과 처리
 * @author Eddie Cho
 * @version 1.0.0
 * @see EbestUserDetailsService#loadUserByUsername(String username)
 * @see LoginSuccessHandler
 */
@Component
public class LoginFailureHandler extends CoTopComponent implements AuthenticationFailureHandler {
	private AdminService adminService;

	// Constructor
	public LoginFailureHandler(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * 로그인 실패 처리
	 * <p>Note: 로그인 실패 세션에 대한 결과 처리를 합니다.
	 * <br>{@link EbestUserDetailsService#loadUserByUsername(String username)}에서 예외가 발생한 로그인 시도나 유효성 검사 실패로 {@code Spring Security}에서 발생시킨 로그인 시도는
	 * 로그인을 시도한 아이디와 해당 로그인에 대한 예외({@link AuthenticationException})를 가지고 아래 메서드를 동작시킵니다.
	 * <br> 커스터마이징된 예외 발생은 {@link EbestUserDetailsService#loadUserByUsername(String username)}에서 관리합니다.
	 * @author Eddie Cho
	 * @param request the request during which the authentication attempt occurred.
	 * @param response the response.
	 * @param exception the exception which was thrown to reject the authentication request.
	 * @since 1.0.0
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter( USERNAME_PARAM );

		// 예외를 확인하여 결과 코드, 메세지 생성
		JsonObject result = parseException(username, exception);

		// 응답 전송
		writeResponse(response, result);

		// 관리자 로그인 로그
		AdminBean admin = new AdminBean(username);
		admin.setResultCode(result.get("resultCode").getAsString());
		adminService.addLoginFailedLog(admin);
	}

	/**
	 * AuthenticationException을 확인하여 결과 코드와 결과 메세지를 생성합니다.
	 * <p>
	 * <i>return Json key 정보</i>
	 * <pre>
	 * resultCode    = 로그인 결과 코드
	 * resultMessage = 로그인 결과 메세지
	 * </pre>
	 * @param username 로그인 시도 아이디
	 * @param exception {@link AuthenticationException}를 상속받는 예외
	 * @return {@link JsonObject}
	 */
	private JsonObject parseException(String username, AuthenticationException exception) {
		String errCode = "99";	// default 99
		String errMsg  = exception.getMessage();

		// 존재하지 않는 아이디
		if( exception instanceof UsernameNotFoundException ) {
			errCode = "90";
			errMsg = "존재하지 않는 아이디 입니다.";
		}

		// 비밀번호 틀림
		else if( exception instanceof BadCredentialsException ) {
			errCode = "91";
			errMsg = "올바른 비밀번호가 아닙니다.";
		}

		login_log.info("['"+username+"'] Log-in Failed. (errMsg: "+errMsg+" )");

		JsonObject result = new JsonObject();
		result.addProperty("resultCode", errCode);
		result.addProperty("resultMessage", errMsg);
		return result;
	}
}
