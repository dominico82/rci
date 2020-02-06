package kr.co.rci.esign.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@PostMapping(value = "post_test")
	public String testPost(HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model) {
		model.addAttribute("request", "Post");

		return "test";
	}

	@GetMapping(value = "get_test")
	public String testGet(HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model) {
		model.addAttribute("request", "Get");

		return "test";
	}

	@RequestMapping(value = "test")
	public String test(HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model) {
		model.addAttribute("request", "Default");

		return "/auth/login";
	}
}
