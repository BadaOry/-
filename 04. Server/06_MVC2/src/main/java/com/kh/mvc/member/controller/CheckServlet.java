package com.kh.mvc.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mvc.member.model.service.MemberService;


@WebServlet("/member/idCheck")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	// ▼ 이 서블렛에서 바로 DB 로 연결하지는 않을 것임
	private MemberService service = new MemberService();

    public CheckServlet() {
    }


    // [ CheckServlet 의 비전 ]
    // - 아이디 중복 체크 요청을 받아서
    // - 실제로 id 가 중복됐는지 MemberService 에게 물어보고
    // - 그 결과값을 객체로 만들어서
    // - Client 에게 JSON 형태로 넘겨줄 것임
    
    @Override
	protected void 	doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ID 값을 가져와서 DB 에 있는 값인지 확인하고 결과(JSON)를 전달하는 기능
    	Map<String, Boolean> map = new HashMap<>();
		String userId = request.getParameter("userId");
		
		System.out.println("UserId : " + userId);

		// ▼ map 객체에 result 를 put 해서 
		//   ▷ 두 번째 매개변수는 id 가 이미 존재하면 true, 없으면 false 리턴할 것임
		//   ▷ 결과값을 객체로 리턴
		map.put("duplicate", service.isDuplicateID(userId));
		
		response.setContentType("application/json; charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());
		
	}

}
