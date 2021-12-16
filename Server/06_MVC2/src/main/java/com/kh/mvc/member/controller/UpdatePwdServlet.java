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


// ▼ 비밀번호 암호화 해야해서 EncrytFilter 의 servletNames 에 추가할 것임
@WebServlet(name="updatePwd", urlPatterns = "/member/updatePwd")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService service = new MemberService();

    public UpdatePwdServlet() {

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ▼ 비밀번호 변경 화면으로 포워딩하는 코드
    	request.getRequestDispatcher("/views/member/updatePwd.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	// ▼ 업데이트 & DELETE 와 마찬가지로, 로그인 후에 요청하는 것인지 확인하는 절차 
    	HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	String userPwd = request.getParameter("userPwd");
    	
    	// ▼ 로그인이 되어있는 상태
    	if(loginMember != null) {
    		// ▼ no 값 가져오기
    		result = service.updatePassword(loginMember.getNo(), userPwd);
    		
    		// ▼ 정상적으로 탈퇴가 된 경우
    		if(result > 0) {
        		request.setAttribute("msg", "비밀번호 변경이 완료되었습니다.");
        		// ▼ 메세지 찍고, msg.jsp 에 script 가 있으면 self.close() 로 창 종료하는 코드가 있음
        		request.setAttribute("script", "self.close()");
    		} else {
        		request.setAttribute("msg", "비밀번호 변경에 실패했습니다.");
    			request.setAttribute("location", "/member/updatePwd");
    		}
    		
		// ▼ 로그인이 안되어있는 상태
    	} else {
    		request.setAttribute("msg", "로그인 후 삭제해 주세요.");
			request.setAttribute("location", "/");
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	
    	System.out.println(userPwd);
    }

    	
}
