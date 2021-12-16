package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet("/member/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// ▼ 새로 작성한 회원 정보는 MemberService 로 넘길 것임
	private MemberService service = new MemberService();
	
    public UpdateServlet() {
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 로그인 된 사용자인지 체크
    	HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	Member member = null;
    	String hobby = null;
    	int result = 0;
    	
    	if(loginMember != null) {
    	// 2. 사용자가 수정한 내용을 가지고 Member 객체를 생성
    		
    		// ▼ session 과 member 가 확인되면 (= 로그인이 잘 되면),
    		//   사용자에게 입력받았던 값을 새 객체로 만들 것임
    		
    		member = new Member();
    		
    		// ▼ UPDATE 쿼리문을 수행시키기 위해 no 값 받아오기
    		//   ▷ 아니면 myPage.jsp 에 input 태그 추가해서 받아올 수도 있음
    		//     단, 이 경우 hidden 으로 만들어도 개발자도구에서 확인할 수 있으므로 비추천
    		member.setNo(loginMember.getNo());
    		
    		member.setId(request.getParameter("userId"));
    		member.setName(request.getParameter("userName"));
    		member.setPhone(request.getParameter("phone"));
    		member.setEmail(request.getParameter("email"));
    		member.setAddress(request.getParameter("address"));
    		hobby = request.getParameterValues("hobby") != null 
    				? String.join(",", request.getParameterValues("hobby")) : null;
    		
    		member.setHobby(hobby);
    
    		// ▼ 새로 입력한 값 받아오는 걸 콘솔에서 확인
    		System.out.println(member);
    		
		// 3. 회원 정보 수정
    		result = service.save(member);
    		
    		// ▼ 쿼리문 수행해서 영향을 받는 행의 갯수 리턴
    		//   ▷ result > 0 : 회원 정보 수정이 잘 되었다는 뜻
    		if(result > 0) {
    			// ▼ 수정 완료 되자마자 수정된 화면을 보여주기 위해, session 갱신
    			session.setAttribute("loginMember", service.findMemberById(loginMember.getId()));
    			
        		request.setAttribute("msg", "회원 정보 수정이 완료되었습니다.");
    			request.setAttribute("location", "/member/myPage");
    		} else {
    			request.setAttribute("msg", "회원 정보 수정이 실패하었습니다.");
    			request.setAttribute("location", "/member/myPage");
    		}
    	} else {
    		request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
    	}
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

    }
}
