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
@WebFilter("/a") // a 가로채기
public class AFilter implements Filter {

	// AFilter aFilter = new AFilter(); // 변수 이름 기본값: 첫 문자는 소문자로 + 나머지
	
	public AFilter() {
		log.info("AFilter 객체 생성 후 자동으로 빈 등록됨");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 선행
		log.info("/a 선행 내용");
		chain.doFilter(request, response);
		// 후행
		log.info("/a 후행 내용");
	}

}

/*
	선행필터 return; 
	// 인증(세션 안에 로그인 정보 X return;), 
	// 인가(세션 안에 로그인 정보 O, 하지만 정보 중 이 요청을 허가할 정보 X) return -> redirect
	
	chain.doFilter(request, response); // 최종목적지 servlet ro jsp or conroller
	
	// 후행필터
*/