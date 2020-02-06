package kr.co.rci.esign.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rci.esign.admin.constant.Url;
import kr.co.rci.esign.admin.domain.AdminBean;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.CustomPageable;
import kr.co.rci.esign.admin.service.AdminService;

@Controller
public class AdminController extends CoTopComponent{

	@Autowired
	AdminService adminService;

	/**
	 * 관리자 목록
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.MANAGER.MANAGER_LIST })
	public String managerList(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			AdminBean bean,
			CustomPageable pageable) {

		log.debug("managerList");

		Page<AdminBean> result = adminService.getManagerList(bean, pageable);

		model.addAttribute("managerList", result);

		return Url.MANAGER.MANAGER_LIST_JSP;
	}

	/**
	 * 관리자 삭제 ajax
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@PostMapping(value={ Url.MANAGER.MANAGER_DELETE_AJAX })
	@ResponseBody
	public Map<String, Object> managerDeleteAjax(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			HttpSession session,
			@RequestBody AdminBean bean) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		bean.setRegNo((String) session.getAttribute("adminNo"));

		try{
			int result = adminService.setManagerDelete(bean);
			if(result > 0){
				resultMap.put("result", "SUCCESS");
			}
			else {
				resultMap.put("result", "FAIL");
				resultMap.put("resultCd", "98");
				resultMap.put("resultMsg", "삭제 시 오류가 발생하였습니다.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			resultMap.put("result", "FAIL");
			resultMap.put("resultCd", "99");
			resultMap.put("resultMsg", "삭제 시 오류가 발생하였습니다.");
		}
		return resultMap;
	}


	/**
	 * 관리자 등록
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.MANAGER.MANAGER_REG })
	public String managerReg(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			AdminBean bean,
			CustomPageable pageable) {

		log.debug("managerList");

		Page<AdminBean> result = adminService.getManagerList(bean, pageable);

		model.addAttribute("managerList", result);

		return Url.MANAGER.MANAGER_REG_JSP;
	}

	/**
	 * 관리자 등록 ajax
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@PostMapping(value={ Url.MANAGER.MANAGER_REG_AJAX })
	@ResponseBody
	public Map<String, Object> managerRegAjax(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			HttpSession session,
			AdminBean bean,
			CustomPageable pageable) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		bean.setRegNo((String) session.getAttribute("adminNo"));
		int idCount = adminService.getIdDupCheck(bean);
		if(idCount > 0){
			resultMap.put("result", "FAIL");
			resultMap.put("resultCd", "99");
			resultMap.put("resultMsg", "아이디 중복 오류 입니다.");
		}
		else {
			// 초기 비밀번호 아이디와 동일하게
			String encPassword = adminService.getCryptUsrPassword(bean.getAdminId(), req);
			bean.setPassword(encPassword);
			try{
				adminService.setManagerInsert(bean);
				resultMap.put("result", "SUCCESS");
			}
			catch(Exception e){
				e.printStackTrace();
				resultMap.put("result", "FAIL");
				resultMap.put("resultCd", "98");
				resultMap.put("resultMsg", "등록처리 중 오류가 발생하였습니다.");
			}
		}

		return resultMap;

	}

	/**
	 * 아이디 중복체크 ajax
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.MANAGER.MANAGER_ID_DUP_CHECK_AJAX })
	@ResponseBody
	public Map<String, Object> managerIdDupCheckAjax(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			HttpSession session,
			AdminBean bean) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		int idCount = adminService.getIdDupCheck(bean);
		if(idCount > 0){
			resultMap.put("result", "FAIL");
			resultMap.put("resultCd", "99");
			resultMap.put("resultMsg", "아이디가 중복됩니다. 다른 아이디를 입력해 주세요.");
		}
		else {
			resultMap.put("result", "SUCCESS");
			resultMap.put("checkId", bean.getAdminId());

		}

		return resultMap;

	}

	/**
	 * 관리자 수정
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.MANAGER.MANAGER_MOD })
	public String managerMod(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			AdminBean bean,
			CustomPageable pageable) {

		log.debug("managerMod");


		AdminBean result = adminService.getManagerInfo(bean);

		model.addAttribute("managerInfo", result);

		return Url.MANAGER.MANAGER_MOD_JSP;
	}

	/**
	 * 관리자 수정 ajax
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@PostMapping(value={ Url.MANAGER.MANAGER_MOD_AJAX })
	@ResponseBody
	public Map<String, Object> managerModAjax(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			HttpSession session,
			AdminBean bean) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		bean.setRegNo((String) session.getAttribute("adminNo"));

		try{
			// 비밀번호가 있으면 암호화 처리
			if(bean != null && StringUtils.isNotEmpty(bean.getPassword())){
				String encPassword = adminService.getCryptUsrPassword(bean.getPassword(), req);
				bean.setPassword(encPassword);
			}

			int result = adminService.setManagerUpdate(bean);
			if(result > 0){
				resultMap.put("result", "SUCCESS");
			}
			else {
				resultMap.put("result", "FAIL");
				resultMap.put("resultCd", "98");
				resultMap.put("resultMsg", "수정 시 오류가 발생하였습니다.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			resultMap.put("result", "FAIL");
			resultMap.put("resultCd", "99");
			resultMap.put("resultMsg", "수정 시 오류가 발생하였습니다.");
		}
		return resultMap;
	}
}
