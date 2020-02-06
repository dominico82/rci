package kr.co.rci.esign.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.rci.esign.admin.constant.Url;
import kr.co.rci.esign.admin.domain.CoTopComponent;

@Controller
public class MainController extends CoTopComponent{
	/**
	 * Main dash board
	 * 메인 대시보드
	 *
	 * @param req the req
	 * @param res the res
	 * @param model the model
	 * @param Bean the bean
	 * @return the string
	 */
	@GetMapping(value={ Url.MAIN.INDEX })
	public String getMain(HttpServletRequest req,
			HttpServletResponse res,
			Model model) {

		log.debug("test");

		return Url.MAIN.INDEX_TILES;
	}
}
