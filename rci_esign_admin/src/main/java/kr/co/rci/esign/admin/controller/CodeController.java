package kr.co.rci.esign.admin.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rci.esign.admin.constant.Url;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.CodeBean;
import kr.co.rci.esign.admin.domain.CustomPageable;
import kr.co.rci.esign.admin.domain.UserBean;
import kr.co.rci.esign.admin.service.CodeService;

@Controller
public class CodeController extends CoTopComponent{
	
	
	@Autowired
	private CodeService codeservice;
	
	/**
	 * SCA 관리 > 가입사용자
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param
	 * @return
	 */
	@GetMapping(value={ Url.CODE.CODE_LIST })
	public String userList(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			CustomPageable pageable,
			CodeBean codebean
			) {
		
		log.debug(":: codeList");
		
		List<CodeBean> codeList =	codeservice.selectCodeGrp();		// 코드그룹 리스트 호출
		model.addAttribute("codeList", codeList);						// 모델에 담기

		
		return Url.CODE.CODE_LIST_JSP;
	}
	
	
	@GetMapping(value= {Url.CODE.CODE_LIST_DETAIL} )
	public String userListDetail(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			CustomPageable pageable,
			CodeBean codebean) {
		log.debug(":: codeListDetail");
		
		List<CodeBean> codeListDetail = codeservice.selectCode(codebean);
		model.addAttribute("codeListDetail", codeListDetail);
			
		return  Url.CODE.CODE_LIST_DETAIL_JSP;
	}
	
	@PostMapping(value= {Url.CODE.CODE_GRP_LIST_INSERT_AJAX} )
	@ResponseBody
	public void userListInsert(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			CustomPageable pageable,
			CodeBean codebean,
			Principal principal) {
		
	//	codebean.setRegUserNo(principal.getName());		//등록자는 현재사용자
		codebean.setRegUsrNo(1);						//DB가  int형이라  임시로 넣어놨고 나중에 위에걸로 바꿔주셔야해요 ..
		codeservice.insertCodeGrp(codebean);			//인서트
	}
	
	
	@PostMapping(value= {Url.CODE.CODE_LIST_INSERT_AJAX} )
	@ResponseBody
	public void userListDetailInsert(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			UserBean bean,
			@RequestBody List<CodeBean> codeBean ,
			CustomPageable pageable,
			Principal principal) {
		
		codeservice.insertCode(codeBean);
		

		
		
//		System.out.println("codebean.getCodeNm()" + codebean.getCodeNm()  );
//		System.out.println("codebean.getCode()" + codebean.getCode()  );
//		
		
//		System.out.println("코드코드코드" +codebean.get(0).getCode());
//		System.out.println("코드코드코드" +codebean.get(0).getCodeNm());
		
		
		//	codebean.setRegUserNo(principal.getName());		//등록자는 현재사용자
//		codebean.setRegUsrNo(1);						//DB가  int형이라  임시로 넣어놨고 나중에 위에걸로 바꿔주셔야해요 ..
//		codeservice.insertCode(codebean);
	}
}
