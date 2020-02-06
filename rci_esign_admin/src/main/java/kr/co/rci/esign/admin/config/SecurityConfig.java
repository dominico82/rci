package kr.co.rci.esign.admin.config;

import static kr.co.rci.esign.admin.constant.ServerConstants.STATIC_RESOURCES_URL_PATTERNS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.rci.esign.admin.constant.ServerConstants;
import kr.co.rci.esign.admin.constant.Url.AUTH;
import kr.co.rci.esign.admin.security.LoginFailureHandler;
import kr.co.rci.esign.admin.security.LoginSuccessHandler;
import kr.co.rci.esign.admin.service.AdminService;
import kr.co.rci.esign.admin.service.AdminUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/** 관리자 아이디 파라미터 이름 : {@value #USERNAME_PARAM} */
	public static final String USERNAME_PARAM = "un";
	/** 관리자 비밀번호 파라미터 이름 : {@value #PASSWORD_PARAK} */
	public static final String PASSWORD_PARAM = "up";

	@Autowired
	private AdminUserDetailsService adminUserDetailsService;
	@Autowired
	private AdminService adminService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Type 1 - 일반 ID/PW (=하드코딩)
//		auth
//			.inMemoryAuthentication()
//				.withUser("admin").password("1234").roles("ADMIN")
//				.and()
//				.withUser("system").password("1234").roles("SYSTEM");

		// Type 2 - 암호화 ID/PW (=하드코딩)
//		 auth
//		 .inMemoryAuthentication()
//		 .passwordEncoder(passwordEncoder())
//		 .withUser("renault").password("$2a$10$wt3xDYQfzP5vtstWZicpjegMlvCzEVDgAWXKSA4ceW31n.wWYRIn.").roles("ADMIN").and()
//		 .withUser("system").password("$2a$10$wt3xDYQfzP5vtstWZicpjegMlvCzEVDgAWXKSA4ceW31n.wWYRIn.").roles("SYSTEM");

		// Type 3 - 암호화 ID/PW (=기본 DB)
//		auth
//			.jdbcAuthentication()
//			.rolePrefix("ROLE_")
//			.passwordEncoder(passwordEncoder())
//			.dataSource(rsmDatasource).usersByUsernameQuery(AppConstBean.SECURITY_USERS_BY_USERNAME_QUERY)
//			.authoritiesByUsernameQuery(AppConstBean.SECURITY_AUTHORITIES_BY_USERNAME_QUERY);

		// Type 4 - UserDetailsService 구현 클래스 사용
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	/**
	 * http 요청 검사
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// replay 어택을 막기 위한 csrf 토큰의 생성을 비활성화(disabled) 처리
			.csrf().disable()

			// 요청에 대한 권한 매핑
			.authorizeRequests()
				.anyRequest().authenticated()		// 모든 요청에 대해 권한 확인이 필요
//				.anyRequest().permitAll()		// 모든 요청에 대해 권한 확인이 필요
				.and()

			// 로그인 화면 설정
			.formLogin()
				.permitAll()	// 로그인 화면은 권한 없이 접근 가능
				.loginPage(AUTH.LOGIN)
				.loginProcessingUrl(AUTH.LOGIN_PROC)
				.successHandler( new LoginSuccessHandler( adminService ) )
				.failureHandler( new LoginFailureHandler( adminService ) )
				.usernameParameter( USERNAME_PARAM )
				.passwordParameter( PASSWORD_PARAM )
				.and()
			.logout()
				.logoutUrl(AUTH.LOGOUT)
//				.invalidateHttpSession(true)
//				.deleteCookies("JSESSIONID")
				.permitAll()
		;
	}

	/**
	 * web요청 검사
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Security Debug
//		web.debug(true);

		web
			.ignoring()
				// static 리소스 경로는 webSecurity 검사 제외
				.antMatchers( STATIC_RESOURCES_URL_PATTERNS )
				.antMatchers( "/imageShow", ServerConstants.HEALTH_CHECK_URL )
		;
		super.configure(web);
	}

	/**
	 * PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * AuthenticationProvider
	 * <p> 관리자의 계정정보는 eBest Database에서 별도로 관리합니다.
	 * <br>관리자의 계정정보를 통해 로그인 인증을 처리합니다.
	 * @return
	 * @see com.renault.ebest.admin.config.DatabaseConfig
	 */
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
		impl.setUserDetailsService(adminUserDetailsService);
		impl.setPasswordEncoder(new BCryptPasswordEncoder());
		impl.setHideUserNotFoundExceptions(false);
		return impl;
	}

//	@Bean
//	public SessionRegistry sessionRegistry() {
//		SessionRegistry sessionRegistry = new SessionRegistryImpl();
//		return sessionRegistry;
//	}
//
//	@Bean
//	public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy() {
//		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlStrategy = new ConcurrentSessionControlAuthenticationStrategy(
//				sessionRegistry());
//		concurrentSessionControlStrategy.setMaximumSessions(1);
//		concurrentSessionControlStrategy.setExceptionIfMaximumExceeded(true);
//
//		return concurrentSessionControlStrategy;
//	}
}
