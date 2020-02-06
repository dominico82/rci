package kr.co.rci.esign.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.rci.esign.admin.domain.CoTopComponent;

@Controller
public class ErrorController extends CoTopComponent{

	@RequestMapping(value = "/errorPage")
	public String errorPage(HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model) {
		model.addAttribute("request", "Default");

		return "error.400";
	}
}
