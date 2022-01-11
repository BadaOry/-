package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.vo.Member;


@WebServlet("/member/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyPageServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내 정보 페이지에 들어가면 원래 회원가입했을 때 썼던 정보게 나오게 할 것임
    	// 그리고 로그인이 안됐을 때 접근 할 수 없도록 할 것임
    	
    	// ▼ Session 객체에 저장된 Member 의 정보를 가져오기
    	//   ▷ false 는 현재 세션이 없으면 null 주도록 하는 것임
    	//     만약 매개값을 안주고 현재 세션이 없으면 새로 세션객체를 만들어서 주기 때문..
    	HttpSession session = request.getSession(false);
    	// ▼ 세션이 이미 존재할 수도 있어서 로그인 멤버로 한 번 더 체크
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	
    	if(loginMember != null) {
    		// ▼ session이 null이 아니면
    		//   요청이 왔을 때 jsp 페이지로 갈 수 있도록 포워드
    		request.getRequestDispatcher("/views/member/myPage.jsp").forward(request, response);
    	} else {
    		// ▼ session 이 null 이면 메세지를 보냄
    		request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	}
	}

}
