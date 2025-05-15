package com.example.mbboard.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/a/*")
public class AAllFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.info("/a/* 선행코드");
		if(true) { // 인증, 인가
			// redirect 후에... return;
			return;
		}
		chain.doFilter(request, response);
		log.info("/a/* 후행코드");
		
		// 인증: 세션에 로그인 정보(예: 로그인한 사용자 ID)가 있는지 확인
		// 인가: 세션에 로그인 정보는 있지만, 해당 자원에 접근할 권한이 있는지 확인
	}
	
}
