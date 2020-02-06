package kr.co.rci.esign.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kr.co.rci.esign.admin.dao.AdminMapper;
import kr.co.rci.esign.admin.domain.AdminBean;
import kr.co.rci.esign.admin.domain.CoTopComponent;

@Service
public class AdminUserDetailsService extends CoTopComponent implements UserDetailsService{

	@Autowired
	AdminMapper adminMapper;

	/**
	 * 사용자 로그인 인증
	 * <p>Note: 로그인을 시도하는 사용자의 아이디({@code username})으로 사용자를 조회합니다.
	 * <br>커스터마이즈된 예외(예: 비밀번호 5회 이상 실패)를 이 메서드에서 작업합니다.
	 * <br>비밀번호 match 확인은 이후 Spring Security에서 진행합니다.
	 * @param username 로그인 시도 아이디
	 * @exception AuthenticationException
	 * @author Eddie Cho
	 * @since 1.0.0
	 */
	public UserDetails loadUserByUsername(String username) throws AuthenticationException {
		login_log.info("['"+username+"'] tries login.");
		AdminBean admin = new AdminBean(username);
		admin = adminMapper.loginAuthentication(admin);

//		// 1. 사용자(관리자) 없음
//		if( admin != null) {
//			List<AdminAuthBean> authorities = adminMapper.selectAdminAuthorities(admin);
//			admin.setAuthorities(authorities);
//		} else {
//			throw new UsernameNotFoundException("아이디 찾을 수 없음");
//		}

		/* 사용자에 대한 로그인 유효성 검사 예제
		 * 발생하는 Exception은 반드시 AuthenticationException을 상속받는 예외여야 합니다.
		 * AuthenticationException의 subClass 확인
		 * https://docs.spring.io/spring-security/site/docs/4.2.4.RELEASE/apidocs/org/springframework/security/core/AuthenticationException.html

		// 2. 비밀번호 변경기간 초과
		final boolean passwordExpired = false; // <- true로 변경 후 테스트
		if ( passwordExpired )
			throw new CredentialsExpiredException("비밀번호 변경 필요 계정");

		// 3. 5회 이상 비밀번호 틀림
		final boolean passwordLocked = false; // <- true로 변경 후 테스트
		if ( passwordLocked ) {
			throw new LockedException("5회 이상 비밀번호 틀림");
		}
		*/
		return admin;
	}
}
