package kr.co.rci.esign.admin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import kr.co.rci.esign.admin.domain.AdminBean;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.service.AdminService;
import kr.co.rci.esign.admin.service.AdminUserDetailsService;

/**
 * 로그인 성공 핸들러
 * <p> 로그인 성공 세션의 결과 처리
 * @author Eddie Cho
 * @version 1.0.0
 * @see EbestUserDetailsService#loadUserByUsername(String username)
 * @see LoginFailureHandler
 */
@Component
public class LoginSuccessHandler extends CoTopComponent implements AuthenticationSuccessHandler {
	private AdminService adminService;

	// Constructor
	public LoginSuccessHandler(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * 로그인 성공 처리
	 * <p>Note: 로그인 성공 세션에 대한 결과 처리를 합니다.
	 * <br>{@link AdminUserDetailsService#loadUserByUsername(String username)}을 예외없이 통과하고 Spring Security의 유효성 검사를 통과하여
	 * 정상적으로 생성된 {@code Authentication}을 가지고 아래 메서드를 동작시킵니다.
	 * <p> 로그인 계정 유효성 검사 커스터마이징(예: 비밀번호 5회 이상 실패시 예외처리)작업은 아래 메서드에서 처리할 경우
	 * HttpSession 내의 SecurityContext 관리와 {@code Authentication}의 정합성 체크가 불안정해집니다.
	 * <br> 커스터마이징된 예외 발생 작업은 {@link AdminUserDetailsService#loadUserByUsername(String username)}에서 작업바랍니다.
	 * @author Eddie Cho
	 * @since 1.0.0
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		String resultCode = "00"; // 성공

		AdminBean user = (AdminBean) auth.getPrincipal();
		user.setResultCode(resultCode);

		login_log.info("['"+user.getAdminId()+"'] Log-in succeed");

		// session setting
		HttpSession session = request.getSession();
		session.setAttribute("adminNo", user.adminNo); // 관리자번호
		session.setAttribute("loginId", user.adminId); // 아이디
		session.setAttribute("realName", user.realName); // 이름
		session.setAttribute("roleTpCd", user.roleTpCd); // 권한


		// Response에 결과 값을 넣어 줌
		JsonObject loginResult = new JsonObject();
		loginResult.addProperty("resultCode", resultCode);
		loginResult.addProperty("targetUrl", request.getContextPath()+"/");
//		loginResult.addProperty("targetUrl", "/main");
		System.out.println("targetUrl: " + request.getContextPath()+"/");
		// 응답 전송
		writeResponse(response, loginResult);

		// 관리자 로그인 로그 (otp 기능 적용하면 otp성공 후로 옮김)
		adminService.addLoginSucceedLog(user);
	}
}
