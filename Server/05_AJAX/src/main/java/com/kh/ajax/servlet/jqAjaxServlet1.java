package com.kh.ajax.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jqAjax1.do")
public class jqAjaxServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public jqAjaxServlet1() {
    }


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("input");
		
		System.out.println("입력 값 : " + input);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter()
		        .print("입력 값 : " + input + ", 길이 : " + input.length());
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
