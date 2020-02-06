package kr.co.rci.esign.admin.domain;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAuthority implements GrantedAuthority {
	private String authority;

	@Override
	public String getAuthority() {
		return this.authority;
	}

	private static final long serialVersionUID = 1L;
}
