package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

/*
 *  [ 우리의 목표 ]
 *  	- Session 으로 구현 : 내장 영역 객체로 브라우저 유지되는 동안 로그인 되도록 구현
 *  	- 사용자에게 아이디, 비밀번호를 입력받아 데이터베이스에서 찾아봄
 *  	- 찾으면 session 영역에 회원 정보를 저장할 것임
 *  	- 그리고 로그인 하면 url이 바뀌지 않고 홈으로 다시 돌아오는 redirect 방식이 좋을 것임
 * 		- 그리고 서블렛이 비즈니스 로직을 수행하지는 않을 것임
 *        ▷ 그래서 Service 객체를 만들어서 비즈니스 로직을 처리할 수 있게 할 것임
 *      - 일단 코드들을 작성하고, 빨간 줄 뜨는 것 들은 ctrl 1 눌러서 클래스 만들고 메소드도 만들 것임
 */
@WebServlet(name="login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// ▼ 비즈니스 로직을 처리할 Service 객체를 만들고,
	//   처리는 객체 service 에게 맡김
	//   ▷ MVC 에서 Model 에 해당하므로, ctrl 1 눌러서 클래스 만들거임
	private MemberService service = new MemberService();
	
    public LoginServlet() {
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
    	String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		// ▼ checkbox 에 value 값을 지정하지 않았을 때,
		//   체크시 on, 미체크시 null 리턴
		String saveId = request.getParameter("saveId");
		
		System.out.println(userId + ", " + userPwd + ", " + saveId);
		
		// ▼ 입력한 id 와 pwd 가 제대로 입력되었는지 콘솔에 출력해주는 코드
		System.out.println(userId + ", " + userPwd);
		
		// ▼ service 객체를 통해 login 메소드를 사용해 로그인 하는 객체 loginMember
		//   ▷ 로그인이 되면 Member 타입의 객체를 넘겨주고,
		//            안되면 null 을 리턴 하도록 만들 것임
		Member loginMember = service.login(userId, userPwd);
		
		System.out.println(loginMember);
		
		// ▼ 아이디 정보를 쿠키에 저장해주는 코드 
		if(saveId != null) {
			// ▼ 현재 전달된 id를 쿠키에 저장할 것임
			
			// 1. 쿠키 생성
			Cookie cookie = new Cookie("saveId", userId);
			
			// 2. 쿠키의 유지시간 지정 후 response 객체에 쿠키를 추가
			//    ▷ -1 : 세션쿠키, 브라우저가 열릴때까지만 보유 (세션과 같음)
			//    ▷ 이외에는 초단위로 저장
			cookie.setMaxAge(259200);    // ▶ 3일동안 유지
			response.addCookie(cookie);  // ▶ 방금 만든 쿠키 추가
		} else {
			// ▼ 기존 쿠키값을 삭제
			//   : 동일한 key 값을 가지는 쿠키 객체를 생성 후, 유지시간을 0으로 설정하면 됨
			Cookie cookie = new Cookie("saveId", "");
			
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		
		// ▼ session 객체에 loginMember 를 담아줄 것임
		//   ▷ loginMember가 null 이면 로그인 실패
		//   ▷ loginMember가 null 이 아니면 로그인 성공
		if(loginMember != null) {
			// ▼ loginMember 객체를 session 객체에 보관하는 코드 (
			//   ▷ attribute (속성)으로 담아줄 것임
			session = request.getSession();
			
			session.setAttribute("loginMember", loginMember);
			
			// ▼ 로그인이 완료되면 메인화면으로 redirect 해주는 코드 
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			// ▼ 로그인이 실패하면 메세지를 띄워주고 메인화면으로 redirect 해주는 코드 
			
			// 1. 공용으로 사용하는 에러 메세지 출력 페이지에 전달해줄 메세지와
			//    이동할 페이지를 request 객체에 저장
			request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			request.setAttribute("location", "/");
			
			// 2. request 객체의 데이터를 유지해서 페이지를 넘기기 위해
			//    RequestDispatcher 를 이용하여 페이지 전환 (forward 방식)
			 request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}
		
	}

}
