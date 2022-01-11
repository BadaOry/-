<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영역 객체</title>
</head>
<body>
	<h2>영역 객체</h2>
	
	<h3>session 영역과 application 영역의 비교</h3>
	<% 
		application.setAttribute("userName", "무닌수");
		session.setAttribute("address", "로스엔젤레스");
	%>
	
	<a href="scopeTest1.jsp">View Details</a>
	
	<h3>page 영역과 request 영역의 비교</h3>
	
	<a href="scopeTest2.jsp">View Details</a>
	<%
		request.setAttribute("requestScope", "requestValue");
		pageContext.setAttribute("pageScope", "pageValue");
	%>
</body>
</html>