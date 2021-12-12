package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogoutServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// [ 로그아웃 순서 ]
		// 1. session 삭제
		//    ▷ 로그인 정보가 session 영역에 속성으로 저장되어 있으니까, 
		//      session 영역을 지워버리는 식으로 로그아웃 할거임
		
		// ▼ false 의 의미
		//   : 현재 세션 객체가 있으면 가져오고, 없으면 null 을 리턴
		//     * true 거나 값이 없으면, 현재 session 객체가 없을 경우 새로 만들어서 가져옴
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate(); // ▶ 세션을 삭제하는 메소드
		}
		
		// 2. 삭제 후 메인 화면으로 redirect
		response.sendRedirect(request.getContextPath() + "/");
		
		
		
	}

}
