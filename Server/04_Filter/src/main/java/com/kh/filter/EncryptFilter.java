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

import com.kh.util.EncryptUtil;
import com.kh.wrapper.EncryptPasswordWrapper;

/* [ 현재 상황 ]
		1. 클라이언트의 요청이 EncodingFitler.java 를 한 번 타고
		   요청방식이 POST 였으므로 UTF-8 으로 인코딩 해준 후
		2. chain.doFilter() 메소드를 통해 
		   EncryptFiler.java 여기로 오게 됨
		3. 그리고 doFilter() 메소드를 통해 JSP Servlet 으로 갈거임
		 
   [ 앞으로의 비전 ]
   		1. EncodingFilter.java & EncryptFilter.java 를 거친 요청이
   		   JSP Servlet 에 도달
   		2. 이후 다시 EncryptFilter.java, ncodingFilter.java 를 순서대로 거쳐
   		   클라이언트에게 응답
 */

@WebFilter(filterName="encryptFilter", urlPatterns = "/views/member/enrollResult.jsp")
public class EncryptFilter implements Filter {


    public EncryptFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// [ EncryptUtil.java 테스트용 ]
		// : password 텍스트 박스에 입력된 값이 암호화되어 출력될 것임
		
//		System.out.println("EncryptFilter 호출");
//		
//		String message = EncryptUtil.oneWayEnc(request.getParameter("password"), "SHA-256");
//		
//		System.out.println(request.getParameter("password") + " : " + message);
		
		
		
		
		// [ Wrapper 생성하기 ]
		
		// ▼ request 의 파라미터 값을 직접 변경할 수 없기 때문에 RequestWrapper 를 생성함
		//   ▷ request 전달 시 HttpServletRequest 로 강제 형변환해서 넘겨줘야함
		EncryptPasswordWrapper wrapper = new EncryptPasswordWrapper((HttpServletRequest) request);
		
		// ▼ request 대신에, 생성한 wrapper로 request를 감싼 객체를 넘겨줌
		chain.doFilter(wrapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
