<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// ▼ 인코딩 설정
	//   : post 방식의 경우, 인코딩해서 넘겨주지 않으므로
	//     인코딩 정보를 쓰지 않으면 한글이 깨져서 나옴
	request.setCharacterEncoding("utf-8");

	// ▼ 변수 설정
	String name = request.getParameter("userName");
	String age = request.getParameter("age");
	String gender = request.getParameter("gender");
	String height = request.getParameter("height");
	String[] foods = request.getParameterValues("food");

	System.out.println(name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 출력 화면</title>
</head>
<body>
	<h2>개인 정보 출력 화면</h2>
	
	<%= name %>님은 <%= age %>세 이고, <%= height %>cm인 <%= gender %> 입니다.
	좋아하는 음식은
	<%
		for(int i = 0; i < foods.length; i++) {
	%>		
		<span style="color:red"><%= foods[i] + " " %></span>
	<% 
		}
	%>
	
</body>
</html>