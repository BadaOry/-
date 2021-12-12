<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// ▼ 이거 안하면 한글 깨져서 옴
	request.setCharacterEncoding("UTF-8");

	String year = "2021";
	//String pName = request.getParameter("pName");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 액션태그 - include Page</title>
</head>
<body>
	<h2>inlcude Page</h2>
	
	<span style="color:gray">▼ include.jsp 에서 includePage.jsp 로 넘어와서 읽는 변수값</span><br>
	Include Page의 year 변수값 :  <%= year %><br><br>
	
	pName : ${ param.pName }
</body>
</html>