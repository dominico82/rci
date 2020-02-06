package kr.co.rci.esign.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import kr.co.rci.esign.admin.dao.AdminMapper;
import kr.co.rci.esign.admin.domain.AdminBean;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.CustomPageable;
import kr.co.rci.esign.admin.util.DateUtils;

@Service
public class AdminService extends CoTopComponent {
	@Autowired
	private AdminMapper adminMapper;

	public void addLoginSucceedLog(AdminBean admin) {
		addLoginLog(admin, true);
	}
	public void addLoginFailedLog(AdminBean admin) {
		addLoginLog(admin, false);
	}

	private void addLoginLog(AdminBean admin, boolean loginSuccess) {
		admin.setLastLogin(DateUtils.currentDateStr());
		try {
			adminMapper.insertLoginLog(admin);
			if( loginSuccess ) adminMapper.updateLastLogin(admin);
		} catch(Exception e) {
			login_log.error("['"+admin.getAdminId()+"'] Failed to inserting log in eBest database.", e);
		}
	}

	public String selectSecretCd(String adminId) {
		// TODO Auto-generated method stub
		return adminMapper.selectSecretCd(adminId);
	}
	public int setUserSecretCd(AdminBean admin) {
		// TODO Auto-generated method stub
		return adminMapper.setUserSecretCd(admin);
	}

	/**
	 *
	 * 패스워드 암호화 처리
	 *
	 * @param
	 * @return
	 */
	public String getCryptUsrPassword(String password, HttpServletRequest req) {
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(req.getServletContext());
		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
		return passwordEncoder.encode(password);
	}

	/**
	 *
	 * 관리자 정보
	 *
	 * @param
	 * @return
	 */
	public AdminBean getManagerInfo(AdminBean bean) {

		return adminMapper.getManagerInfo(bean);
	}

	/**
	 *
	 * 관리자 조회
	 *
	 * @param
	 * @return
	 */
	public Page<AdminBean> getManagerList(AdminBean bean, CustomPageable pageable) {

		int total = adminMapper.getManagerListCount(bean);
		List<AdminBean> result = adminMapper.getManagerList(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	}

	/**
	 *
	 * 관리자 삭제
	 *
	 * @param
	 * @return
	 */
	public int setManagerDelete(AdminBean bean) {

		return adminMapper.setManagerDelete(bean);
	}

	/**
	 *
	 * 관리자 아이디 중복체크
	 *
	 * @param
	 * @return
	 */
	public int getIdDupCheck(AdminBean bean) {

		return adminMapper.getIdDupCheck(bean);
	}

	/**
	 *
	 * 관리자 등록 처리
	 *
	 * @param
	 * @return
	 */
	public int setManagerInsert(AdminBean bean) {

		return adminMapper.setManagerInsert(bean);
	}


	/**
	 *
	 * 관리자 수정 처리
	 *
	 * @param
	 * @return
	 */
	public int setManagerUpdate(AdminBean bean) {

		return adminMapper.setManagerUpdate(bean);
	}

}
