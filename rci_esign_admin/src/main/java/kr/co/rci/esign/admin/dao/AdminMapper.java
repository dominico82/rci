package kr.co.rci.esign.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.rci.esign.admin.domain.AdminAuthBean;
import kr.co.rci.esign.admin.domain.AdminBean;
import kr.co.rci.esign.admin.domain.CustomPageable;

/**
 * admin dao
 * @author
 * @version 1.0.0
 */
public interface AdminMapper {
	/**
	 * 관리자 로그인 인증
	 * @param admin
	 * @return
	 */
	AdminBean loginAuthentication(AdminBean admin);

	List<AdminAuthBean> selectAdminAuthorities(AdminBean admin);

	int updateLastLogin(AdminBean admin);

	void insertLoginLog(AdminBean admin);

	/*
	 * secretCd 조회
	 */
	String selectSecretCd(String adminId);

	/*
	 * secretCd update
	 */
	 int setUserSecretCd(AdminBean ebestAdmin);

	 /**
	 * 관리자정보
	 * @param admin
	 * @return
	 */
	AdminBean getManagerInfo(AdminBean bean);

	/**
	 * 관리자조회
	 * @param admin
	 * @return
	 */
	List<AdminBean> getManagerList(@Param("scParam") AdminBean bean, @Param("pageable") CustomPageable pageable);

	int getManagerListCount(@Param("scParam") AdminBean bean);

	/**
	 * 관리자 아이디 중복체크
	 * @param admin
	 * @return
	 */
	int setManagerDelete(AdminBean bean);

	/**
	 * 관리자 아이디 중복체크
	 * @param admin
	 * @return
	 */
	int getIdDupCheck(AdminBean bean);

	/**
	 * 관리자등록
	 * @param admin
	 * @return
	 */
	int setManagerInsert(AdminBean bean);

	/**
	 * 관리자정보 수정
	 * @param admin
	 * @return
	 */
	int setManagerUpdate(AdminBean bean);
}
