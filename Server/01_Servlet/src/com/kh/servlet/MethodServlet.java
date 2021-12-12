package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// ▼ URL mapping 을 두 개 이상 넣을 경우 배열로 전달해 줌
@WebServlet("/method.do")
public class MethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public MethodServlet() {
    }


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ▼ - 사용자가 보낸 정보(데이터)들은 request 객체 안에
    	//     키(name 속성의 값), 값(value 속성의 값) 형태로 저장됨
    	//   - 키, value 값은 & 로 구분
    	//   - 해당 name 속성을 가지는 요소의 value 값을 문자열로 읽어옴
    	String userName = request.getParameter("userName");
    	String age = request.getParameter("age");
    	String gender = request.getParameter("gender");
    	String height = request.getParameter("height");
    	// ▼ 여러개의 값은 getParameter 로 못가져옴
    	//   : 여러개를 선택하고, food2까지 실행해봐도 
    	//     가장 먼저 받는 한개의 키밸루만 가져옴
//    	String food1 = request.getParameter("food");
//    	String food2 = request.getParameter("food");
    	
    	// ▼ 해당 name 속성을 가지는 요소들의 value 값들을 문자열의 배열로 읽어옴
    	String[] foods = request.getParameterValues("food");
    	
    	System.out.println(userName);
    	System.out.println(age);
    	System.out.println(gender);
    	System.out.println(height);
    	
    	// ▼ String 배열을 출력하는 람다식
    	Arrays.stream(foods).forEach((food) -> System.out.println(food));
	
    	// ▼ 위에서 사용자가 request 객체에 정보를 넣어 요청한 내용을
    	//   response 객체에 담아 응답 화면에 대한 설정하기 위한 코드 
    	//   : 응답 화면이 문서 형태의 html 이고, 문자셋(인코딩)은 UTF-8이라는 뜻
    	response.setContentType("text/html;charset=UTF-8");
    	
    	// ▼ 사용자에게 응답 화면을 출력하기 위한 스트림 생성 (문자 기반 출력 스트림)
    	PrintWriter out = response.getWriter();
    	
    	// ▼ 자바 코드로 응답 화면을 작성하는 과정
    	out.write("<html>");
    	out.write("<body>");
    	out.write("<h1>개인 정보 출력 화면</h1>");
    	out.printf("%s님은 %s세 이고, 키가 %scm 인 %s 입니다. 좋아하는 음식은 ", userName, age, height, gender);
    	Arrays.stream(foods).forEach((food) -> out.write(food + " "));
    	out.write("입니다.");
    	out.write("</body>");
    	out.write("</html>");
    }


    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ▼ POST 방식으로 넘겨받은 데이터가 영어 또는 숫자가 아닌 경우, 아래와 같은 인코딩 처리 필요
    	//   - GET 방식의 경우 인코딩 설정이 필요없는 이유는
    	//     URL에 값들이 포함되어서 전달되기 때문
    	//   - POST 방식의 경우 BODY 에 값을 포함헤서 전달하는데
    	//     기본적으로 ISO-8859-1로 인코딩되었다고 간주하므로, 인코딩 처리 필요
    	request.setCharacterEncoding("UTF-8");
    	
    	this.doGet(request, response);
	}

}
