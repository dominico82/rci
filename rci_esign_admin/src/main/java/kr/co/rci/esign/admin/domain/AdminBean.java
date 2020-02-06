package kr.co.rci.esign.admin.domain;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBean extends ComBean implements UserDetails {
	/** 관리자 번호 */
	public String adminNo;
	/** 관리자 아이디 */
	public String adminId;
	/** 관리자 비밀번호 */
	public String password;
	/** 관리자 실제 이름 */
	public String realName;
	/** 관리자 권한 코드 */
	public String roleTpCd;
	/** 관리자 권한 코드명 */
	public String roleTpCdNm;
	/** 관리자 최종 로그인 일자 */
	public String lastLogin;
	/** 관리자 사용 가능 여부 */
	public boolean enabled;
	/** 관리자 권한 리스트 */
	public List<AdminAuthBean> authorities;
	/** 관리자리스트 */
	public List<AdminBean> adminList;

	/** 관리자 등록일 */
	public String regDt;
	/** 관리자번호*/
	public String regNo;
	/** 관리자 수정일 */
	public String modDt;
	/** 관리자번호 */
	public String modNo;
	/** 관리자 이메일 */
	public String adminEmail;
	/** 관리자 휴대폰 */
	public String adminHp1;
	public String adminHp2;
	public String adminHp3;
	/** 관리자 카운트 */
	public String adminCnt;
	/** 관리자 권한 */
	public String authority;
	/** 관리자 권한명 */
	public String roleName;
	/** 관리자 권한설명 */
	public String description;

	/** 관리자 검색 - 관리자 등록일 (2018-06-15 석민현) */
	public String strRegDt;
	public String endRegDt;

	private String secretCd; 		//secret코드

	private String encodeKey;		//encodeKey

	public AdminBean() {super();}
	public AdminBean(String adminId) {
		this.adminId = adminId;
	}

	@Override
	public String getUsername() {
		return this.adminId;
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

	private static final long serialVersionUID = 1L;
}
