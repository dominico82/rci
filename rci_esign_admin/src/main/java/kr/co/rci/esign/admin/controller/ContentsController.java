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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rci.esign.admin.constant.Url;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.ContentsBean;
import kr.co.rci.esign.admin.domain.CustomPageable;
import kr.co.rci.esign.admin.service.ContentsService;

@Controller
public class ContentsController extends CoTopComponent{

	@Autowired
	ContentsService contentsService;

	/**
	 * 컨텐츠 관리 - 이미지관리
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@GetMapping(value = Url.CONTENTS.VISUAL_SETTING)
	public String visualSetting(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			ContentsBean bean) {

		ContentsBean mainImgInfo = new ContentsBean();

		mainImgInfo = contentsService.getMainImageInfo(bean);

		model.addAttribute("mainImgInfo",mainImgInfo);

		return Url.CONTENTS.VISUAL_SETTING_JSP;
	}

	/**
	 * 컨텐츠 관리 - 이미지관리저장
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@PostMapping(value = Url.CONTENTS.VISUAL_SETTING_UPDATE)
	@ResponseBody
	public Map<String, Object> updateVisualSetting(HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			HttpSession session,
			ContentsBean bean) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		bean.setRegNo((String) session.getAttribute("adminNo"));

		int result = contentsService.updateMainImageInfo(bean);
		if(result > 0){
			resultMap.put("result", "SUCCESS");
		}
		else {
			resultMap.put("result", "FAIL");
			resultMap.put("resultCd", "");
			resultMap.put("resultMsg", "저장실패");
		}

		return resultMap;
	}

	/**
	 * 컨텐츠 관리 - 공지사항 목록
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={ Url.CONTENTS.NOTICE_LIST })
	public String notiList(@ModelAttribute ContentsBean bean, HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			CustomPageable pageable) {

		log.debug("boardList");

		Page<ContentsBean> result = contentsService.getNoticeList(bean, pageable);

		model.addAttribute("boardList", result);

		return Url.CONTENTS.NOTICE_LIST_JSP;
	}


	/**
	 * Contents Management
	 * 컨텐츠 관리 - 공지사항 보기
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={Url.CONTENTS.NOTICE_VIEW})
    public String viewForm(@ModelAttribute ContentsBean bean, Model model, HttpServletRequest request) throws Exception{

        String postNo = request.getParameter("postNo");
        bean.setPostNo(postNo);

        ContentsBean result = contentsService.selectNoticeByNo(bean);

        model.addAttribute("boardList", result);

        return Url.CONTENTS.NOTICE_VIEW_JSP;
    }

	/**
	 * Contents Management
	 * 컨텐츠 관리 - 공지사항 폼
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={ Url.CONTENTS.NOTICE_DETAIL })
	public String notiDetail(@ModelAttribute ContentsBean bean, HttpServletRequest req,
			HttpServletResponse res,
			Model model
			) {
		return Url.CONTENTS.NOTICE_DETAIL_JSP;
	}

	/**
	 * Contents Management
	 * 컨텐츠 관리 - 공지사항 등록
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={ Url.CONTENTS.NOTICE_WRITE })
	public String write(@ModelAttribute ContentsBean bean, Model model, HttpServletRequest request) throws Exception {

		String isUse = request.getParameter("isUse");
		String popupUseYn = request.getParameter("popupUseYn");

		if(isUse=="on"){
			bean.setIsUse("1");
		}else{
			bean.setIsUse("0");
		}

		if(popupUseYn=="on"){
			bean.setPopupUseYn("Y");
		}else{
			bean.setPopupUseYn("N");
		}

		contentsService.insertBoard(bean);

		return "redirect:"+Url.CONTENTS.NOTICE_LIST;
	}


	/**
	 * Contents Management
	 * 컨텐츠 관리 - 공지사항 수정
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
  @RequestMapping(value=Url.CONTENTS.NOTICE_UPDATE)
    public String updateBoard(@ModelAttribute ContentsBean bean, Model model, HttpServletRequest request) throws Exception{

	  String postNo = request.getParameter("postNo");
	  String title = request.getParameter("title");
      bean.setPostNo(postNo);
      bean.setTitle(title);
	  contentsService.updateBoard(bean);

	  return "redirect:"+Url.CONTENTS.NOTICE_LIST;
    }


  /**
	 * Contents Management
	 * 컨텐츠 관리 - 공지사항 삭제
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={ Url.CONTENTS.NOTICE_DELETE })
	public String delete(@RequestParam(value="postNo") int postNo) throws Exception {

		contentsService.deleteBoard(postNo);

		return "redirect:"+Url.CONTENTS.NOTICE_LIST;
	}


	/**
	 * Contents Management
	 * 컨텐츠 관리 - FAQ 목록
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={ Url.CONTENTS.FAQ_LIST })
	public String faqList(@ModelAttribute ContentsBean bean, HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			CustomPageable pageable) {

		log.debug("boardList");

		Page<ContentsBean> result = contentsService.getFaqList(bean, pageable);

		model.addAttribute("boardList", result);

		return Url.CONTENTS.FAQ_LIST_JSP;
	}


	/**
	 * Contents Management
	 * 컨텐츠 관리 - FAQ 등록 & 상세보기
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={Url.CONTENTS.FAQ_DETAIL})
	public String faqDetail(@ModelAttribute ContentsBean bean, HttpServletRequest req,
			HttpServletResponse res,
			Model model
			) {
	return Url.CONTENTS.FAQ_DETAIL_JSP;
	}


	/**
	 * Contents Management
	 * 컨텐츠 관리 - GUIDE
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={Url.CONTENTS.USER_GUIDE_LIST})
	public String guideList(@ModelAttribute ContentsBean bean, HttpServletRequest req,
			HttpServletResponse res,
			Model model,
			CustomPageable pageable) {

		log.debug("boardList");

		Page<ContentsBean> result = contentsService.getGuideList(bean, pageable);

		model.addAttribute("boardList", result);

		return Url.CONTENTS.USER_GUIDE_LIST_JSP;
	}


	/**
	 * Contents Management
	 * 컨텐츠 관리 - GUIDE 등록 & 상세보기
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@RequestMapping(value={ Url.CONTENTS.USER_GUIDE_DETAIL })
	public String guideDetail(@ModelAttribute ContentsBean bean, HttpServletRequest req,
			HttpServletResponse res,
			Model model
			) {
		return Url.CONTENTS.USER_GUIDE_DETAIL_JSP;
	}
}
