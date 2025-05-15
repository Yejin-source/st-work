package com.example.mbboard.filter;

import java.io.IOException;

import com.example.mbboard.dto.Member;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request instanceof HttpServletRequest) {
			HttpServletRequest httpReq = (HttpServletRequest)request; // 강제 형변환
			HttpSession session = httpReq.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			
			// 로그인 상태가 아닌 경우 (인증)
			if(loginMember == null) {
				if(response instanceof HttpServletResponse) {
					log.info("OnSessionFilter에 걸려서 sendRedirect /login 된 것");
					((HttpServletResponse) response).sendRedirect("/login");
				return;
				}
			} 
				
			// 로그인 상태지만 ROLE이 되지 않는 경우 (인가)
			if(loginMember.getMemberRole().equals("ADMIN") == false) {
				if(response instanceof HttpServletResponse) {
					log.info("OnSessionFilter에 걸려서 sendRedirect /member/info 된 것");
					((HttpServletResponse) response).sendRedirect("/member/info");
				return;
				}
			}
		}
		
		chain.doFilter(request, response);
		
	}
}
