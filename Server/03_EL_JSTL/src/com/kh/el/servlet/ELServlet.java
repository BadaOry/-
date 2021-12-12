package com.kh.el.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.el.model.vo.Person;

@WebServlet("/el.do")
public class ELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ELServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// 서블릿에서 request, session, application 객체를 가져와서 
    	// 데이터를 setAttribute() 에 담아서 el.jsp에 전달하려고 함 !!
    	
    	// ▼ request 객체의 session, application 내장 객체 가져옴
    	HttpSession session = request.getSession();
    	ServletContext application = request.getServletContext();
    	
    	// ▼ Request Scope 에 데이터를 저장함
    	request.setAttribute("classRoom", "R 강의장");
    	request.setAttribute("student", new Person("홍길동", 20, '남'));
    	request.setAttribute("scope", "request 영역");
    	
    	// ▼ Session Scope 에 데이터 저장
    	session.setAttribute("academy", "KH 정보교육원");
    	session.setAttribute("teacher", new Person("무닌수", 20, '남'));
    	session.setAttribute("scope", "Session 영역");
    	
    	// ▼ Application Scope 에 데이터 저장
    	application.setAttribute("scope", "Application 영역");
    	
    	// ▼ getRequestDispatcher 메소드를 통해 views/el/el.jsp로
    	//   request 객체와 response 객체를 담아서 forward 방식으로 보낼것임 
    	//   ▷ 여기 내용이 el.jsp 에서 출력될 것임
		request.getRequestDispatcher("views/el/el.jsp").forward(request, response);
	}

}
