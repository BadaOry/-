package com.kh.mvc.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="enroll", urlPatterns = "/*")
public class EncodingFilter implements Filter {


    public EncodingFilter() {
    }


	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hr = (HttpServletRequest) request;
		String requestMethod = hr.getMethod(); // ▷ 전송 방식을 가져오는 메소드
		
		if(requestMethod.equals("POST")) {
			hr.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(hr, response);
	}

	public void destroy() {

	}

}
