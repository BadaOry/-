package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name="enroll", urlPatterns="/member/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService service = new MemberService();

    public EnrollServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ▼ 회원가입 페이지 (enroll.jsp) 로 전환해주는 기능
    	
    	request.getRequestDispatcher("/views/member/enroll.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ▼ ﻿회원가입 정보가 넘어왔을 때 회원가입을 시키는 비즈니스 로직
    	//   ▷ Member 객체를 만들어서 DB의 한 행으로 저장할 것임
    	//     (데이터를 가져올 때는 DB 의 한 행을 자바의 한 객체에 넣었었음)
    	
    	Member member = new Member();
    	
    	member.setId(request.getParameter("userId"));
    	member.setPassword(request.getParameter("userPwd"));
    	member.setName(request.getParameter("userName"));
    	member.setPhone(request.getParameter("phone"));
    	member.setEmail(request.getParameter("email"));
    	member.setAddress(request.getParameter("address"));
//    	// ▼ 배열로 리턴하기 때문에 에러남
//    	member.setHobby(request.getParameter("hobby"));
    	// ▼ 배열의 값들을 , 로 구분하여 하나의 문자열로 받아오는 코드
    	member.setHobby(String.join(",", request.getParameterValues("hobby")));
    		
    	System.out.println(member);
    	
    	int result = service.save(member);
    	
    	// ▼ result > 0 : 변화하는 행이 있다
    	//   ▷ 행이 존재한다  ▶ 회원가입 성공
    
    	if(result > 0) {
    		// [ 회원 가입 완료 ]
    		request.setAttribute("msg", "회원 가입 성공!");
			request.setAttribute("location", "/");
    	} else {
    		// [ 회원 가입 실패 ]
    		request.setAttribute("msg", "!!!!!회원 가입 실패!!!!!");
    		// ▼ 실패하면 다시 enroll.jsp 를 열 수 있도록 하는 코드
    		request.setAttribute("location", "/member/enroll");
    	}
    	
    	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
