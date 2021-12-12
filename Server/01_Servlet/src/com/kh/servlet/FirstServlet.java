package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 서버에서 서블릿을 실행시키는 방법
 * 1. 서블릿 클래스 작성
 * 		- javax.servlet.http.HttpServlet 을 상속하는 클래스 생성
 * 		- doGet(), doPost() 메소드 재정의
 * 2. 서블릿 등록 및 URL 매핑
 *      1) web.xml 에 작성한 서블릿 등록 및 URL 매핑
 *      2) @WebServlet으로 서블릿 등록 및 URL 매핑
 */


//@WebServlet("/first.do")
//          ▼ 노란 줄 뜨면 ctrl 1 눌러서 add genereated Serial version ID 추가
public class FirstServlet extends HttpServlet{

	private static final long serialVersionUID = 204599706914368718L;
	
	// ▼ 기본 생성자는 습관처럼 만들어 놓는게 좋음
	public FirstServlet() {
	}
	
	// ▼ alt shift s  > Override/Implement method > doGet 선택하여 생성
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ▼ GET 방식의 요청에 응답할 로직을 구현
		System.out.println("사용자로부터 GET 요청을 받음.");
		
		// ▼ 응답해주는 데이터의 타입이 html이고, 인코딩 방식이 utf-8이라는 뜻
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>우리가 만든 첫 번째 서블릿이 반환한 내용</h1>");
		out.write("</body>");
		out.write("</html>");
	}
}