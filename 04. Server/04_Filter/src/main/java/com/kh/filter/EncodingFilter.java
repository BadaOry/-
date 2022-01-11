package com.kh.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// - 서블릿 필터는 request, response 가 서블릿이나 JSP 등의 리소스에 도달하기 전에 필요한 전/후 처리 작업을 처리함
// - 서블릿 필터는 FilterChain 을 통해 여러 개의 필터를 연속적으로 사용할 수 있음
@WebFilter(filterName="encodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {


    public EncodingFilter() {
    }

    // ▼ 컨테이너가 필터 생성 시, 초기화를 위해 호출
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(fConfig.getFilterName() + "필터가 생성되어 초기화가 진행");
	}

	// ▼ 컨테이너가 현재 요청에 필터를 적용하겠다고 판단 시 호출
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("서블릿 동작 전 코드 실행");

		// ▼ doFilter 의 첫 번째 매개값 ServletRequest 가져오기
		//   : 여기서 request 는 http 로 가져오는거라 HttpServletRequest 로 강제 형변환 함
		//     ▷ 형변환 해줘야만 get 어쩌구 메소드들을 사용할 수 있음
		HttpServletRequest hr = (HttpServletRequest) request;
		String requestMethod = hr.getMethod(); // ▷ 전송 방식을 가져오는 메소드
		
		System.out.println("요청 방식 : " + requestMethod);
		
		if(requestMethod.equals("POST")) {
			hr.setCharacterEncoding("UTF-8");
			
			System.out.println(request.getCharacterEncoding() + " 인코딩 완료");
		}
		
		// ▼ doFilter 로 마지막 작업이 끝나면 요청과 응답을 보내주는 것
		//   ▷ 다음 필터를 호출하거나 (Filter > Filter)
		//   ▷ 마지막이면 Filter > Servlet 또는 JSP 호출
		chain.doFilter(hr, response);
		
		System.out.println("서블릿 동작 후 코드 실행");
	}

    // ▼ 컨테이너가 필터를 제거할 때 호출
	public void destroy() {
		System.out.println("필터가 소멸되어 destroy() 호출");
	}

}
