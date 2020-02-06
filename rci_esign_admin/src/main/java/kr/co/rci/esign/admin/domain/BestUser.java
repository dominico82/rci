package kr.co.rci.esign.admin.domain;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

/**
 * Renault-Samsung Motors API를 사용하는 영업사원 정보
 * @author Eddie Cho
 * @version 1.0.0
 */
@Getter @Setter
public class BestUser extends ComBean implements UserDetails {
	/** 사용자 아이디 */
	private String userId;
	/** 사용자 비밀번호 */
	private String password;

	/** 사용자 이름 */
	private String realname;
	/** 사용자 이름(영문) */
	private String userengName;
	/** 부서코드 */
	private String deptCode;
	/** 계정 사용 가능 여부 */
	private boolean enabled;

	private String branchName;	// 지점명
	private String branchCode;	// 지점코드
	private String branchFlag;	// 지점 플래그

	/** 사용자 권한 */
	private List<UserAuthority> authorities;

	public BestUser() {super();};
	public BestUser(String userId) {
		super();
		this.userId = userId;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 로그인 결과 코드
	 *
	 * @author Eddie Cho
	 */
	public static final class LoginResult {
		/** 로그인 성공 */
		public static final String SUCCESS = "00";

		// -----------------------------------------------------------------------------
		// 보안 문제
		// -----------------------------------------------------------------------------
		public static final String UNIDENTIFIED_SYSTEM = "40";

		// -----------------------------------------------------------------------------
		// 계정 문제
		// -----------------------------------------------------------------------------
		/** 존재하지 않는 아이디  */
		public static final String NOT_EXIST           = "90";
		/** 비밀번호 불일치 */
		public static final String NOT_MATCHED_PASSWD  = "91";
		/** 비밀번호 5회 오류 */
		public static final String PASSWORD_ATTEMPTS_EXCEED     = "92";
		/** 알 수 없는 에러  */
		public static final String UNEXPECTED_ERR      = "99";
	}

	private static final long serialVersionUID = 1L;
}
