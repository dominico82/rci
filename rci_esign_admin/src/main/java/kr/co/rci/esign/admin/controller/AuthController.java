package kr.co.rci.esign.admin.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.rci.esign.admin.constant.Url;

/**
 * AuthController.java
 * <br>관리자 권한 처리 컨트롤러
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
@Controller
public class AuthController {
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	/**
	 * 로그인 페이지 호출
	 */
	@GetMapping(Url.AUTH.LOGIN)
	public String loginPage(Principal principal) {
		log.debug("로그인 화면 진입");
		if( principal != null ) {
			log.debug("["+principal.getName()+"] 이미 로그인한 사용자 입니다.");
		}
		return Url.AUTH.LOGIN_JSP;
	}
}
