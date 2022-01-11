package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ▼ 이클립스는 Annotation 을 기본으로 서블릿 생성
// () 에서 ctrl space 누르면 다양한 속성들을 볼 수 있음
@WebServlet(name="/second.do", urlPatterns = "/second.do")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SecondServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at : ").append(request.getContextPath());
		
//		response.setContentType("text/html;charset=utf-8");
//		
//		PrintWriter out = response.getWriter();
//		
//		out.write("<html>");
//		out.write("<body>");
//		out.write("<h1>우리가 만든 첫 번째 두 번째 서블릿이 반환한 내용</h1>");
//		out.write("</body>");
//		out.write("</html>");
	}

}
