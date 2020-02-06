package kr.co.rci.esign.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import kr.co.rci.esign.admin.dao.UserMapper;
import kr.co.rci.esign.admin.domain.CustomPageable;
import kr.co.rci.esign.admin.domain.UserBean;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	/**
	 *
	 * 사용자조회
	 *
	 * @param
	 * @return
	 */
	public Page<UserBean> getUserList(UserBean bean, CustomPageable pageable) {

		int total = userMapper.getUserListCount(bean);
		List<UserBean> result = userMapper.getUserList(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	}

	/**
	 *
	 * 로그인이력조회
	 *
	 * @param
	 * @return
	 */
	public Page<UserBean> getUserLoginHist(UserBean bean, CustomPageable pageable) {

		int total = userMapper.getUserLoginHistCount(bean);
		List<UserBean> result = userMapper.getUserLoginHist(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	}

	/**
	 *
	 * 휴면사용자 조회
	 *
	 * @param
	 * @return
	 */
	public Page<UserBean> getUserDormantList(UserBean bean, CustomPageable pageable) {

		int total = userMapper.getUserDormantListCount(bean);
		List<UserBean> result = userMapper.getUserDormantList(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	}

	/**
	 *
	 * 사용자 상태 업데이트
	 *
	 * @param
	 * @return
	 */
	public int setUserStatCdUpdate(UserBean bean) {

		int result = userMapper.setUserStatCdUpdate(bean);

		return result;
	}

	/**
	 *
	 * 사용자 사용여부 업데이트
	 *
	 * @param
	 * @return
	 */
	public int setUserIsUseUpdate(UserBean bean) {

		return userMapper.setUserIsUseUpdate(bean);
	}

	/**
	 *
	 * 전체사용자조회
	 *
	 * @param
	 * @return
	 */
	public Page<UserBean> getUserAllList(UserBean bean, CustomPageable pageable) {

		int total = userMapper.getUserAllListCount(bean);
		List<UserBean> result = userMapper.getUserAllList(bean, pageable);

		return new PageImpl<>(result, pageable, total);
	}
}
