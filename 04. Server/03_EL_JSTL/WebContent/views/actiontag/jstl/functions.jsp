<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Functions</title>
</head>
<body>
	<h2>JSTL Functions</h2>
	
	<c:set var="str" value="java oracle HTML CSS jQuery Servlet JSP AJAX" />
	
	str : ${ str }
	
	<ul>
		<%--               ▼ 함수를 호출하는 것으로, 매개값으로 길이를 알고싶은 변수를 넘기면 됨 --%>
		<li>문자열의 길이 : ${ fn:length(str) }</li>
		<li>모두 대문자로 : ${ fn:toUpperCase(str) }</li>
		<li>모두 소문자로 : ${ fn:toLowerCase(str) }</li>
		<li>CSS 의 시작 위치는 ${ fn:indexOf(str, 'CSS') } 입니다.</li>
		<li>java 를 javascript로 변경 : ${ fn:replace(str, 'java', 'javascript') }</li>
		<li></li>
		<li></li>
	</ul>
	
	<!-- ▼ 원본이 바뀌는 것은 아님 -->
	str : ${ str }
	
	
</body>
</html>