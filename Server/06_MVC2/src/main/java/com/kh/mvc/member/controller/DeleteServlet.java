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


@WebServlet("/member/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // ▼ 삭제 로직은 DeleteServlet 에서 하지 않고, MembeService 에게 위임
	private MemberService service = new MemberService();

    public DeleteServlet() {

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	// ▼ 업데이트와 마찬가지로, 로그인 후에 요청하는 것인지 확인하는 절차 
    	HttpSession session = request.getSession(false);
    	Member loginMember = session != null ? (Member) session.getAttribute("loginMember") : null;
    	
    	// ▼ 로그인이 되어있는 상태
    	if(loginMember != null) {
    		result = service.delete(loginMember.getNo());
    		
    		// ▼ 정상적으로 탈퇴가 된 경우
    		if(result > 0) {
        		request.setAttribute("msg", "정상적으로 탈퇴되었습니다.");
        		// ▼ location 을 logout 으로 주는 이유
        		//   ▷ 탈퇴 된 것이 session 에 적용되지는 않으므로, 
        		//      LogoutServlet 으로 요청을 보내 session 을 삭제 후 홈으로 넘어가기 위함
    			request.setAttribute("location", "/logout");
    		} else {
        		request.setAttribute("msg", "탈퇴에 실패했습니다.");
    			request.setAttribute("location", "/member/myPage");
    		}
    		
		// ▼ 로그인이 안되어있는 상태
    	} else {
    		request.setAttribute("msg", "로그인 후 탈퇴해 주세요.");
			request.setAttribute("location", "/");
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
