package com.example.mbboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mbboard.dto.Member;
import com.example.mbboard.service.ILoginService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired ILoginService loginService;
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Member paramMember) {
		Member loginMember = loginService.login(paramMember);
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
		}
		return "/member/memberHome";
	}
	
	
	// 세션 안의 상세정보를 보여주는 요청 -> 로그인 상태에서는 요청 가능 -> 필터1
	@GetMapping("/member/info") 
	public String info() {
		return "/member/info";
	}
	
	
	// 관리자 페이지 요청 -> 로그인 상태이며 role이 'ADMIN'인 경우 요청 가능 -> 필터2
	@GetMapping("/admin/adminHome") 
	public String adminHome() {
		return "/admin/adminHome";
	}
}
