package kr.co.rci.esign.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rci.esign.admin.constant.Url;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.CustomPageable;
import kr.co.rci.esign.admin.domain.UserBean;
import kr.co.rci.esign.admin.service.UserService;

@Controller
public class UserController extends CoTopComponent{

	@Autowired
	UserService userService;

	/**
	 * SCA 관리 > 가입사용자
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.USER.USER_LIST })
	public String userList(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			CustomPageable pageable) {

		log.debug("userList");

		Page<UserBean> result = userService.getUserList(bean, pageable);

		model.addAttribute("userList", result);

		return Url.USER.USER_LIST_JSP;
	}


	/**
	 * SCA 관리 > 로그인이력
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.USER.USER_LOGIN_HIST })
	public String userLoginHist(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			CustomPageable pageable) {

		log.debug("userLoginHist");

		Page<UserBean> result = userService.getUserLoginHist(bean, pageable);

		model.addAttribute("userList", result);

		return Url.USER.USER_LOGIN_HIST_JSP;
	}



	/**
	 * SCA 관리 > 휴면/퇴사사용자
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.USER.USER_DORMANT_LIST })
	public String userDormantList(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			CustomPageable pageable) {

		log.debug("userDormantList");

		Page<UserBean> result = userService.getUserDormantList(bean, pageable);

		model.addAttribute("userList", result);

		return Url.USER.USER_DORMANT_LIST_JSP;
	}

	/**
	 * 사용자 상태변경 ajax
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@PostMapping(value={ Url.USER.USER_STAT_CD_UPDATE_AJAX })
	@ResponseBody
	public Map<String, Object> userStatCdUpdate(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			HttpSession session,
			@RequestBody UserBean bean) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		bean.setCreateId((String) session.getAttribute("loginId"));

		try{
			int result = userService.setUserStatCdUpdate(bean);
			if(result > 0){
				resultMap.put("result", "SUCCESS");
			}
			else {
				resultMap.put("result", "FAIL");
				resultMap.put("resultCd", "98");
				resultMap.put("resultMsg", "저장 시 오류가 발생하였습니다.");
			}
		}
		catch(Exception e){
			resultMap.put("result", "FAIL");
			resultMap.put("resultCd", "99");
			resultMap.put("resultMsg", "저장 시 오류가 발생하였습니다.");
		}
		return resultMap;
	}

	/**
	 * 사용자 사용여부 업데이트 ajax
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@PostMapping(value={ Url.USER.USER_IS_USE_UPDATE_AJAX })
	@ResponseBody
	public Map<String, Object> userIsUseUpdate(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			HttpSession session,
			@RequestBody UserBean bean) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		bean.setCreateId((String) session.getAttribute("loginId"));

		try{
			int result = userService.setUserIsUseUpdate(bean);
			if(result > 0){
				resultMap.put("result", "SUCCESS");
			}
			else {
				resultMap.put("result", "FAIL");
				resultMap.put("resultCd", "98");
				resultMap.put("resultMsg", "저장 시 오류가 발생하였습니다.");
			}
		}
		catch(Exception e){
			resultMap.put("result", "FAIL");
			resultMap.put("resultCd", "99");
			resultMap.put("resultMsg", "저장 시 오류가 발생하였습니다.");
		}
		return resultMap;
	}



	/**
	 * SCA 관리 > 전체사용자
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.USER.USER_TOTAL_LIST })
	public String userAllList(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			CustomPageable pageable) {

		log.debug("userTotalList");
		Page<UserBean> result = userService.getUserAllList(bean, pageable);

		model.addAttribute("userList", result);

		return Url.USER.USER_TOTAL_LIST_JSP;
	}
}
