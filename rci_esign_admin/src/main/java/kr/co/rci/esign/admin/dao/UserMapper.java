package kr.co.rci.esign.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.rci.esign.admin.domain.CustomPageable;
import kr.co.rci.esign.admin.domain.UserBean;

public interface UserMapper {

	/**
	 * 사용자조회
	 * @param UserBean
	 * @return
	 */
	public List<UserBean> getUserList(@Param("scParam") UserBean bean, @Param("pageable") CustomPageable pageable);

	public int getUserListCount(@Param("scParam") UserBean bean);

	/**
	 * 로그인이력조회
	 * @param UserBean
	 * @return
	 */
	public List<UserBean> getUserLoginHist(@Param("scParam") UserBean bean, @Param("pageable") CustomPageable pageable);

	public int getUserLoginHistCount(@Param("scParam") UserBean bean);

	/**
	 * 휴면사용자조회
	 * @param UserBean
	 * @return
	 */
	public List<UserBean> getUserDormantList(@Param("scParam") UserBean bean, @Param("pageable") CustomPageable pageable);

	public int getUserDormantListCount(@Param("scParam") UserBean bean);

	/**
	 * 사용자상태 업데이트
	 * @param UserBean
	 * @return
	 */
	public int setUserStatCdUpdate(UserBean bean);

	/**
	 * 사용자 사용여부 업데이트
	 * @param UserBean
	 * @return
	 */
	public int setUserIsUseUpdate(UserBean bean);

	/**
	 * 전체사용자조회
	 * @param UserBean
	 * @return
	 */
	public List<UserBean> getUserAllList(@Param("scParam") UserBean bean, @Param("pageable") CustomPageable pageable);

	public int getUserAllListCount(@Param("scParam") UserBean bean);
}
